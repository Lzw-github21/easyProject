package project.entity.basic.datatable;

import project.entity.basic.IgnoreCaseMap;
import project.utils.Convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataRow extends IgnoreCaseMap<String, Object> {
    private DataRowState rowState = DataRowState.Unchanged;

    /**
     * 获取行状态
     *
     * @return
     */
    public DataRowState getRowState() {
        return rowState;
    }

    /**
     * 设置行状态
     *
     * @return
     */
    public void setRowState(DataRowState rowState) {
        this.rowState = rowState;
    }

    /**
     * 获取字符串
     *
     * @return
     */
    public String getString(String colName) {
        Object o = this.get(colName);
        return o == null ? "" : o.toString();
    }

    /**
     * 获取日期
     *
     * @param colName
     * @return
     */
    public Date getDate(String colName) throws ParseException {
        Object o = this.get(colName);
        return o == null ? null : Convert.ConverToDate(o.toString());
    }

    /**
     * 获取数值
     *
     * @return
     */
    public int getInt(String colName) {
        Object o = this.get(colName);
        return o == null ? null : Integer.parseInt(o.toString());
    }


    /**
     * 获取数值
     *
     * @return
     */
    public Long getLong(String colName) {
        Object o = this.get(colName);
        return o == null ? null : Long.parseLong(o.toString());
    }

    /**
     * 获取Boolean
     *
     * @return
     */
    public Boolean getBoolean(String colName) {
        return Boolean.parseBoolean(this.getString(colName));
    }

    private Date dateFormat(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(dateString);
    }

    public Object getFirstValue() {
        if (this.size() == 0) {
            return null;
        }
        return this.values().iterator().next();
    }
}
