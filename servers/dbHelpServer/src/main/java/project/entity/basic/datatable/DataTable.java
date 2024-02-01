package project.entity.basic.datatable;

import com.alibaba.fastjson.JSONObject;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.*;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import org.dom4j.DocumentException;
import project.utils.Convert;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DataTable extends LinkedList<DataRow> {
    public DataTable() {
    }

    public DataTable(String name) {
        this.name = name;
    }

    /**
     * 根据行数组创建表格对象
     *
     * @param rows
     */
    public DataTable(DataRow[] rows) {
        for (DataRow row : rows) {
            this.add(row);
        }
    }

    private String name = "table";

    public String getName() {
        return name;
    }

    public boolean HasRow() {
        return this.size() > 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    //列信息
    private HashMap<String,DataColumn> columns;


    public HashMap<String,DataColumn> getColumns() {
        return columns;
    }

    public void setColumns(HashMap<String,DataColumn> columns) {
        this.columns = columns;
    }

    /**
     * 排序（只能按照数值字段）
     *
     * @param sortField
     * @return
     */
    public DataRow[] SortAsc(String sortField) {
        List<DataRow> drs = this.stream().sorted(new Comparator<DataRow>() {
            @Override
            public int compare(DataRow o1, DataRow o2) {
                return o1.getInt(sortField) - o2.getInt(sortField);
            }
        }).collect(Collectors.toList());
        return drs.toArray(new DataRow[drs.size()]);
    }


    /**
     * 过滤
     *
     * @param filter
     * @return
     * @throws
     */
    public DataRow[] Select(String filter) throws Exception {
        Expression expression = CCJSqlParserUtil.parseCondExpression(filter);
        List<DataRow> drs = new LinkedList<DataRow>();
        for (DataRow drTable : this) {
            if (this.assertExpression(drTable, expression)) drs.add(drTable);
        }
        return drs.toArray(new DataRow[drs.size()]);
    }

    private boolean assertExpression(DataRow dr, Expression expression) {
        if (expression instanceof AndExpression) {//如果是and
            return this.assertExpression(dr, ((AndExpression) expression).getLeftExpression()) &&
                    this.assertExpression(dr, ((AndExpression) expression).getRightExpression());
        } else if (expression instanceof OrExpression) {//如果是or
            return this.assertExpression(dr, ((OrExpression) expression).getLeftExpression()) ||
                    this.assertExpression(dr, ((OrExpression) expression).getRightExpression());
        } else if (expression instanceof Parenthesis) {//括号
            return assertExpression(dr, ((Parenthesis) expression).getExpression());
        } else if (expression instanceof ComparisonOperator) {//比较
            ComparisonOperator compar = (ComparisonOperator) expression;
            Expression field = compar.getLeftExpression();//字段
            Expression val = compar.getRightExpression();//值
            Object oVal = null;
            if (val instanceof StringValue) { //字符
                oVal = ((StringValue) val).getValue();
            } else if (val instanceof LongValue) {//数字
                oVal = ((LongValue) val).getValue();
            }

            if (expression instanceof EqualsTo) {//等于
                return oVal.toString().equals(dr.getString(field.toString()));
            } else if (expression instanceof NotEqualsTo) {//不等于
                return oVal.toString().equals(dr.getString(field.toString())) == false;
            } else if (expression instanceof GreaterThan) {//大于
                return dr.getInt(field.toString()) > Integer.parseInt(oVal.toString());
            } else if (expression instanceof GreaterThanEquals) {//大于等于
                return dr.getInt(field.toString()) >= Integer.parseInt(oVal.toString());
            } else if (expression instanceof MinorThan) {//小于
                return dr.getInt(field.toString()) < Integer.parseInt(oVal.toString());
            } else if (expression instanceof MinorThanEquals) {//小于等于
                return dr.getInt(field.toString()) <= Integer.parseInt(oVal.toString());
            }
        } else if (expression instanceof InExpression) {//In
            InExpression inExp = (InExpression) expression;
            Expression field = inExp.getLeftExpression();
            ItemsList rightItemsList = inExp.getRightItemsList();
            ExpressionList list = (ExpressionList) rightItemsList;
            String val = dr.getString(field.toString());

            boolean have = false;
            for (Expression item : list.getExpressions()) {
                if (item instanceof StringValue) { //字符
                    have = have || val.equals(((StringValue) item).getValue());
                } else if (item instanceof LongValue) {//数字
                    have = have || (Long.parseLong(val) == ((LongValue) item).getValue());
                }
            }
            return have;
        }
        return true;
    }

    @Override
    public DataTable clone() {
        return new DataTable(this.toArray(new DataRow[this.size()]));
    }

    public DataTable copy() {
        DataTable dt = new DataTable();
        for (DataRow row:this
             ) {
            DataRow ndr = new DataRow();
            for (String key : row.keySet()
                 ) {
                ndr.put(key,row.get(key));
                row.clone();
            }
            dt.add(ndr);
        }
        return dt;
    }

    /*
     * 将DataTable中的列名转为大写，返回一个新的DataTable
     * */
    public DataTable toUpperColumn(){
        DataTable newdt = new DataTable();
        for (DataRow dataRow : this){
            DataRow result = new DataRow();
            Set<Map.Entry<String,Object>> entrySet = dataRow.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                result.put(key.toUpperCase(), entry.getValue());
            }
            newdt.add(result);
        }
        newdt.setColumns(this.getColumns());
        return newdt;
    }

    /**
     * 读取.net生成的XML
     *
     * @param dotnetXmlString
     */
    public void readDotnetXmlString(String dotnetXmlString) throws DocumentException, UnsupportedEncodingException {
        DataSet ds = new DataSet();
        ds.readDotnetXmlString(dotnetXmlString);
        if (ds.size() > 0) {
            this.clear();
            for (DataRow dr : ds.get(0)) {
                this.push(dr);
            }
        }
    }

    public void readJsonString(String JSONString) throws Exception {
        //日期格式转换
        SimpleDateFormat formatTwo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.UK);
        List<LinkedHashMap> rows = JSONObject.parseArray(JSONString, LinkedHashMap.class);
        for (LinkedHashMap map : rows) {
            DataRow dataRow = new DataRow();
            map.forEach((h, q) -> {
                if(this.columns!=null){
                    DataColumn dc = this.columns.get(h);
                    try {
                        q = Convert.ConvertSimpleType(q,dc.getDataType());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                dataRow.put(h.toString(), q);
            });
            this.add(dataRow);
        }
    }

}
