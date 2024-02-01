package project.entity.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import project.entity.basic.datatable.DataSet;
import project.entity.basic.datatable.DataTable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JsonData extends HashMap<String,Object> {
    @Override
    public String toString() {
        return JsonData.toString(this,SerializerFeature.WriteMapNullValue);
    }

    public Date getDate(String key,String pattern) throws ParseException {
        Object o = this.get(key);
        if(o==null) {
            return  null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       return simpleDateFormat.parse( o.toString() );
    }

    /**
     * 序列化对象
     * @param obj
     * @return
     */
    public static String toString(Object obj){
        //lzl 2020-03-10 禁用循环引用
        return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 序列化对象
     * @param obj
     * @return
     */
    public static String toString(Object obj,SerializerFeature SerializerFeature){
        //lzl 2020-03-10 禁用循环引用
        return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
    }

    /**
     * 从字符串获得
     * @param json
     * @return
     */
    public static JsonData fromString(String json){
        return JSON.parseObject(json,JsonData.class);
    }

    /**
     * 转换成对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> clazz) throws Exception {
        if(clazz == DataSet.class){
            DataSet ds = new DataSet();
            ds.readJsonString(json);
            return (T) ds;
        }else if(clazz == DataTable.class){
            DataTable dt = new DataTable();
            dt.readJsonString(json);
            return (T) dt;
        }
        return JSON.parseObject(json,clazz);

    }

    /**
     * 转换成List
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json, Class<T> clazz){
        return JSON.parseArray(json,clazz);
    }
}
