package project.entity.basic.datatable;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DataSet extends LinkedHashMap<String, DataTable> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 是否包含表
     *
     * @param tableName
     * @return
     */
    public boolean containsTable(String tableName) {
        for (DataTable table : this.values()) {
            if (table.getName().toUpperCase().equals(tableName.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取表
     *
     * @param tableName
     * @return
     */
    public DataTable getTable(String tableName) {
        for (DataTable table : this.values()) {
            if (table.getName().equalsIgnoreCase(tableName)) {
                return table;
            }
        }
        return null;
    }

    /**
     * 获取第一张表
     * @return
     */
    public DataTable getFirstTable() {
        if(this.size()==0) return null;
        return this.values().iterator().next();
    }

    /**
     * 读取.net生成的XML
     *
     * @param dotnetXmlString
     */
    public void readDotnetXmlString(String dotnetXmlString) throws DocumentException, UnsupportedEncodingException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new ByteArrayInputStream(dotnetXmlString.getBytes("UTF-8")));
        Element root = document.getRootElement();

        //如果根是DIFFGRAM，剥掉一层
        if(root.getName().toUpperCase().equals("DIFFGRAM")){
            root = root.elementIterator().next();
        }

        // iterate through child elements of root
        for (Iterator<Element> it = root.elementIterator(); it.hasNext(); ) {
            Element element = it.next();
            String eleName = element.getName().toUpperCase();
            //去掉结构化的前部
            if (eleName.equals("SCHEMA")) continue;
            this.addDataTable(element);
        }
    }

    private void addDataTable(Element element){
        String name = element.getName().toUpperCase();
        //创建表
        DataTable dt = this.getTable(name);
        if (dt == null) {
            dt = new DataTable(name);
            this.add(dt);
        }

        //创建行
        if (element.elements().size() > 0) {
            DataRow newDr = new DataRow();
            for (Iterator<Element> itRow = element.elementIterator(); itRow.hasNext(); ) {
                Element cele = itRow.next();
                newDr.put(cele.getName(), cele.getData());
            }
            dt.add(newDr);
        }
    }

    /**
     * 读取Json数据
     * @param strJson
     */
    public void readJsonString(String strJson) throws Exception{
        JSONObject jsonObject = JSONObject.parseObject(strJson);
        jsonObject.forEach((k, v) -> {
            Object o = jsonObject.get(k);
            DataTable dt = new DataTable(k.toString());
            try {
                dt.readJsonString(JSONObject.toJSONString(o));
            } catch (Exception e) {
                throw new  RuntimeException(e);
            }
            this.add(dt);
        });
    }

    public void add(DataTable dt) {
        this.put(dt.getName(), dt);
    }
}
