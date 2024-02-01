package project.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import project.entity.basic.JsonData;
import project.entity.basic.datatable.DataRow;
import project.entity.basic.datatable.DataTable;
import sun.misc.BASE64Decoder;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/*
* java各种日期格式化
"2014-03-12 12:05:34",
"2014-03-12 12:05",
"2014-03-12 12",
"2014-03-12",
"2014-03",
"2014",
"20140312120534",
"2014/03/12 12:05:34",
"2014/3/12 12:5:34",
"2014年3月12日 13时5分34秒",
"201403121205",
"1234567890",
"20140312",
"201403",
"2000 13 33 13 13 13",
"30.12.2013",
"12.21.2013",
"21.1",
"13:05:34",
"12:05",
"14.1.8",
"14.10.18"
* */

public class Convert {
    /**
     * 转换Int 转换失败统一返回0
     *
     * @param val
     * @return
     */
    public static int parseInt(Object val) {
        if (val == null) return 0;
        String ss = val.toString();
        if (ss.isEmpty()) return 0;
        BigDecimal db = new BigDecimal(ss);
        String ii = db.toPlainString();
        try {
            return Integer.parseInt(ii);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 转换double 转换失败统一返回0
     *
     * @param val
     * @return
     */
    public static double parseDouble(Object val) {
        if (val == null) return 0;
        String ss = val.toString();
        if (ss.isEmpty()) return 0;
        try {
            return Double.parseDouble(ss);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 把Blob类型转换为byte数组类型
     *
     * @param blob
     * @return
     */
    public static byte[] parseBytes(Blob blob) {
        BufferedInputStream is = null;
        try {
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;
            while (offset < len
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {
                offset += read;
            }
            return bytes;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                return null;
            }
        }
    }


    public static String parseString(Clob clob) {
        Reader is = null;// 得到流
        try {
            String reString = "";
            is = clob.getCharacterStream();// 得到流
            BufferedReader br = new BufferedReader(is);
            String s = br.readLine();
            StringBuffer sb = new StringBuffer();
            while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
                sb.append(s);
                s = br.readLine();
            }
            reString = sb.toString();
            return reString;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                is.close();
                is = null;
            } catch (IOException e) {
                return null;
            }
        }
    }
    public static String clobToString(Object clobPar){
        String valueStr = "";
        try {
            if (clobPar instanceof Clob) {
                Clob clob = (Clob) clobPar;
                StringBuffer strOut = new StringBuffer();
                String aux;
                BufferedReader br = new BufferedReader(clob.getCharacterStream());
                while ((aux = br.readLine()) != null) {
                    strOut.append(aux);
                    strOut.append("\n");
                }
                if (strOut.length() > 0) {
                    valueStr = strOut.substring(0, strOut.length() - 1);
                }
            }
        }catch (Exception ex){

        }
        return valueStr;
    }
    /**
     * 将DataTable转换成泛型List
     * @param tClass
     * @param datable
     * @param <T>
     * @return
     */
    public static <T> List<T> parseList(Class<T> tClass , DataTable datable) {
        String json = JsonData.toString(datable);
        return JSON.parseArray(json,tClass);
    }

    /**
     * 将Map转换成泛型对象
     * @param tClass
     * @param map
     * @param <T>
     * @return
     */
    public static <T> T parseObject(Class<T> tClass , Map map) {
        String json = JsonData.toString(map);
        return JSON.parseObject(json,tClass);
    }

    public static String getTimeDes(Long ms) {
        //处理参数为NULL的情况
        if(ms == null){
            return "";
        }
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuilder str=new StringBuilder();
        if(day>0){
            str.append(day).append("天,");
        }
        if(hour>0){
            str.append(hour).append("小时,");
        }
        if(minute>0){
            str.append(minute).append("分钟,");
        }
        if(second>0){
            str.append(second).append("秒,");
        }
        if(milliSecond>0){
            str.append(milliSecond).append("毫秒,");
        }
        if(str.length()>0){
            str=str.deleteCharAt(str.length()-1);
        }

        return str.toString();
    }

    public static String byte2HexString(byte[] b) {
        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] newChar = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            newChar[2 * i] = hex[(b[i] & 0xf0) >> 4];
            newChar[2 * i + 1] = hex[b[i] & 0xf];
        }
        return new String(newChar);
    }

    public static byte[] hexString2ByteArray(String hexString) {
        char[] chars = hexString.toCharArray();
        byte[] b = new byte[chars.length / 2];
        for (int i = 0; i < b.length; i++) {
            int high = Character.digit(chars[2 * i], 16) << 4;
            int low = Character.digit(chars[2 * i + 1], 16);
            b[i] = (byte) (high | low);
        }
        return b;
    }


    /**
     * 转换对象为String,若为null则返回空值
     */
    public static String ExToString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    /**
     * Base64字符串 转换为 byte数组
     */
    private byte[] ConverToBytes(String base64) throws Exception {
        try {
            return new BASE64Decoder().decodeBuffer(base64);
        } catch (IOException e) {
            throw new Exception("转换字节码数组错误", e);
        }
    }

    /**
     * 转换字符串日期为date类型
     */
    private static Date ConverToDate(String date, String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(date);
    }

    /**
     * 转换任意格式字符串日期为date类型
     */
    public static Date ConverToDate(String dateStr) {

        HashMap<String, String> dateRegFormat = new HashMap<String, String>();
        dateRegFormat.put(
                "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$",
                "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
        dateRegFormat.put(
                "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,3}\\D*$",
                "yyyy-MM-dd-HH-mm-ss");//2014年3月12日 13时5分34秒，2014-03-12 12:05:34，2014/3/12 12:5:34
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
                "yyyy-MM-dd-HH-mm");//2014-03-12 12:05

        dateRegFormat.put("^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$","yyyy-MM-dd");
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}\\D+\\d{2}$",
                "yyyy-MM-dd-HH");//2014-03-12 12
        dateRegFormat.put("^\\d{4}\\D+\\d{2}\\D+\\d{2}$", "yyyy-MM-dd");//2014-03-12
        dateRegFormat.put("^\\d{4}\\D+\\d{2}$", "yyyy-MM");//2014-03
        dateRegFormat.put("^\\d{4}$", "yyyy");//2014
        dateRegFormat.put("^\\d{14}$", "yyyyMMddHHmmss");//20140312120534
        dateRegFormat.put("^\\d{12}$", "yyyyMMddHHmm");//201403121205
        dateRegFormat.put("^\\d{10}$", "yyyyMMddHH");//2014031212
        dateRegFormat.put("^\\d{8}$", "yyyyMMdd");//20140312
        dateRegFormat.put("^\\d{6}$", "yyyyMM");//201403
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$",
                "yyyy-MM-dd-HH-mm-ss");//13:05:34  拼接当前日期
        dateRegFormat.put("^\\d{2}\\s*:\\s*\\d{2}$", "yyyy-MM-dd-HH-mm");//13:05  拼接当前日期
        dateRegFormat.put("^\\d{2}\\D+\\d{1,2}\\D+\\d{1,2}$", "yy-MM-dd");//14.10.18(年.月.日)
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}$", "yyyy-dd-MM");//30.12(日.月) 拼接当前年份
        dateRegFormat.put("^\\d{1,2}\\D+\\d{1,2}\\D+\\d{4}$", "dd-MM-yyyy");//12.21.2013(日.月.年)

        String curDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        DateFormat formatter2;
        String dateReplace;
        Date dateSuccess = null;
        try {
            for (String key : dateRegFormat.keySet()) {
                if (Pattern.compile(key).matcher(dateStr).matches()) {
                    formatter2 = new SimpleDateFormat(dateRegFormat.get(key));
                    if (key.equals("^\\d{2}\\s*:\\s*\\d{2}\\s*:\\s*\\d{2}$")
                            || key.equals("^\\d{2}\\s*:\\s*\\d{2}$")) {//13:05:34 或 13:05 拼接当前日期
                        dateStr = curDate + "-" + dateStr;
                    } else if (key.equals("^\\d{1,2}\\D+\\d{1,2}$")) {//21.1 (日.月) 拼接当前年份
                        dateStr = curDate.substring(0, 4) + "-" + dateStr;
                    }
                    dateReplace = dateStr.replaceAll("\\D+", "-");
                    // System.out.println(dateRegExpArr[i]);
                    dateSuccess = formatter2.parse(dateReplace);
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception("日期格式无效" + dateStr);
        } finally {
            return dateSuccess;
        }
    }

    /**
     * 字符串转Double
     */
    public static Double StrToDouble(String str) throws Exception {
        Double double1 = 0.00;
        try {
            double1 = Double.parseDouble(str);
            return double1;
        } catch (Exception e) {
            throw new Exception("字符串转double错误", e);
        }
    }

    /**
     * 字符串转BigDecimal
     */

    public static BigDecimal StrTiBigdecimal(String str) throws Exception {
        BigDecimal bigDecimal = null;
        try {
            bigDecimal = new BigDecimal(str);
            return bigDecimal;
        } catch (Exception e) {
            throw new Exception("字符串转BigDecimal错误", e);
        }
    }

    /**
     * 字符串转时间戳
     */
    public static Timestamp StrToTimeStamp(String str) throws Exception {
        Timestamp timestamp = null;
        try {
            timestamp = Timestamp.valueOf(str);
            return timestamp;
        } catch (Exception e) {
            throw new Exception("字符串转时间戳错误", e);
        }
    }

    /**
     * 如果是bool，但是目标类型是数字
     */
    public static String ConvertBoolToNumber(Boolean bol) {
        return bol ? "1" : "0";
    }

    /**
     * 转换成不一定的简单类型
     */
    public static Object ConvertSimpleType(Object value, Class toClass) throws IOException, ParseException {
        //如果原始值为null，或者与目标类型相匹配
        if (value == null || toClass.equals(value.getClass())) {
            return value;
        }
        //定义返回值returnobj
        Object returnobj = null;
        String str = value.toString();
        //如果值是NAN ,则返回空
        if("NAN".equalsIgnoreCase(str)){
            return null;
        }
        //如果目标是BigDecimal，全部转为double
        if (toClass.equals(BigDecimal.class)) {
            toClass = Double.class;
            returnobj = Double.parseDouble(str);
        }
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //如果目标是boolean
        if (toClass.equals(Boolean.TYPE) || toClass.equals(boolean.class)) {
            returnobj = Boolean.parseBoolean(str);
        }
        //如果目标是int
        if (toClass.equals(Integer.TYPE) || toClass.equals(int.class)) {
            returnobj = Integer.parseInt(str);
        }
        //如果目标是java.util.Date
        if (toClass.equals(Date.class)) {
            //Date date = ConverToDate(str, "yyyy-MM-dd HH:mm:ss");
            Date date = ConverToDate(str);
            returnobj = date;
        }
        //如果目标是java.sql.Date
        if (toClass.equals(java.sql.Date.class)) {
            Date date = ConverToDate(str, "yyyy-MM-dd HH:mm:ss");
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            returnobj = sqldate;
        }
        //如果目标是byte[]
        if (toClass.equals(byte[].class)) {
            returnobj = new BASE64Decoder().decodeBuffer(str);
        }
        //如果是boolean，但是目标类型是数字
        if (("TRUE".equals(str.toUpperCase()) || "FALSE".equals(str.toUpperCase()))
                && (toClass.equals(Double.TYPE) || toClass.equals(Integer.TYPE) || toClass.equals(int.class) || toClass.equals(double.class))) {
            returnobj = str.toUpperCase().equals("TRUE") ? "1" : "0";
        }
        if (returnobj == null) {
            return value;
        }
        return returnobj;
    }


    /*
     * 将DataTable中的列名转为大写
     * */
    public static DataTable ConvertToUpperColumn(DataTable dt){
        if (dt == null || dt.isEmpty()) {
            return null;
        }
        DataTable newdt = new DataTable();
        for (DataRow dataRow : dt){
            DataRow result = new DataRow();
            Set<Map.Entry<String,Object>> entrySet = dataRow.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                String key = entry.getKey();
                result.put(key.toUpperCase(), entry.getValue());
            }
            newdt.add(result);
        }
        newdt.setColumns(dt.getColumns());
        return newdt;
    }
}
