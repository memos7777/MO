package com.mialab.common.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.mialab.common.json.JsonMapParameter;
import com.mialab.common.json.JsonObject;
import com.mialab.common.json.JsonParameterObject;
import com.mialab.common.json.JsonSimpleObject;

public class TextUtils {

    static Logger logger = Logger.getLogger(TextUtils.class.getName());

    public static boolean isEmpty(CharSequence s) {
        if (s == null || s.length() == 0 || s.equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyString(String s) {
        if (s == null || s.trim().length() == 0 || s.equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {
        if (s == null || s.trim().length() == 0 || s.equals("null")) {
            return false;
        }
        return true;
    }

    public static String decodeName(String accountName) {
        if (accountName == null) {
            return null;
        }
        try {
            return new String(accountName.getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        }
        return "";
    }

    public static String byteCountToMBSize(long size) {
        DecimalFormat df = new DecimalFormat("###.##");
        float f;
        if (size < 1024 * 1024) {
            f = (float) ((float) size / (float) 1024);
            return (df.format(new Float(f).doubleValue()) + "KB");
        } else {
            f = (float) ((float) size / (float) (1024 * 1024));
            return (df.format(new Float(f).doubleValue()) + "MB");
        }
    }

    public static String byteCountToKBSize(long size) {
        DecimalFormat df = new DecimalFormat("###.##");
        float f = (float) ((float) size / (float) 1024);
        return (df.format(new Float(f).doubleValue()));
    }

    public static String jsonEncode(String s) {
        if (s == null) {
            return "";
        }
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char tempChar = s.charAt(i);
            switch (tempChar) {
            case '"':
                str.append('\'');
                break;
            case '{':
                str.append('<');
                break;
            case '}':
                str.append('>');
                break;
            case '\\':
                str.append("\\\\");
                break;
            case '/':
                str.append("\\/");
                break;
            case '\b':
                str.append("\\b");
                break;
            case '\t':
                str.append("\\n");
                break;
            case '\n':
                str.append("\\n");
                break;
            case '\f':
                str.append("\\f");
                break;
            case '\r':
                str.append("\\n");
                break;
            case ':':
                str.append("-");
                break;
            default:
                str.append(tempChar);
                break;
            }
        }
        return str.toString().replace("\\n\\n", "\\n");
    }

    public static String paserLottery6Plus1(String s) {
        int pos = s.lastIndexOf(";");
        if (pos >= 0)
            return s.substring(0, pos + 1) + "+" + parseStringToAnimals(s.substring(pos + 1));
        return s;
    }

    public static String getLastString(String s, String regex) {
        int pos = s.lastIndexOf(regex);
        if (pos >= 0)
            return s.substring(pos + 1);
        return s;
    }

    public static String replaceLottery(String s) {
        int pos = s.lastIndexOf(";");
        if (pos >= 0)
            return s.substring(0, pos + 1) + "+" + s.substring(pos + 1);
        return s;
    }

    public static String replaceLastString(String s, String regex, String replaceStr) {
        int pos = s.lastIndexOf(regex);
        if (pos >= 0)
            return s.substring(0, pos + 1) + replaceStr;
        return s;
    }

    public static String cutLastString(String s, String regex) {
        boolean pos = s.endsWith(regex);
        if (pos)
            return s.substring(0, s.lastIndexOf(regex));
        return s;
    }

    public static short parseShort(String str) {
        if (str == null) {
            return 0;
        }
        short i = 0;
        try {
            i = Short.parseShort(str);
        } catch (Exception e) {

        }
        return i;
    }

    public static int parseNumber(String str) {
        if (str == null) {
            return 0;
        }
        int i = 0;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {

        }
        return i;
    }

    public static long parseLong(String str) {
        if (str == null) {
            return 0;
        }
        long i = 0;
        try {
            i = Long.parseLong(str);
        } catch (Exception e) {

        }
        return i;
    }

    public static double parseDouble(String str) {
        if (str == null) {
            return 0;
        }
        double i = 0;
        try {
            i = Double.parseDouble(str);
        } catch (Exception e) {

        }
        return i;
    }

    public static <M, N> String parseJsonMapParameterList(List<? extends JsonMapParameter<M, N>> obj, Map<M, N> para) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (obj != null) {
            for (int i = 0; i < obj.size(); i++) {
                JsonMapParameter<M, N> bean = obj.get(i);
                sb.append(bean.toJSONString(para));
                if (i != (obj.size() - 1)) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String parseJsonParameterList(List<? extends JsonParameterObject> obj, String para) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (obj != null) {
            for (int i = 0; i < obj.size(); i++) {
                JsonParameterObject bean = obj.get(i);
                sb.append(bean.toJSONString(para));
                if (i != (obj.size() - 1)) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String parseJsonList(List<? extends JsonObject> obj) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (obj != null) {
            for (int i = 0; i < obj.size(); i++) {
                JsonObject bean = obj.get(i);
                sb.append(bean.toJSONString());
                if (i != (obj.size() - 1)) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String parseSimpleJsonList(List<? extends JsonSimpleObject> obj) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (obj != null) {
            for (int i = 0; i < obj.size(); i++) {
                JsonSimpleObject bean = obj.get(i);
                sb.append(bean.toSimpleJSONString());
                if (i != (obj.size() - 1)) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /*
     * 通用处理，但是该方法还不完善，有待完善
     */
    public static String parseJsonListUseReflect(List<?> obj, String methodName, Class<?> clazz) {
        Method method = null;
        StringBuffer sb = new StringBuffer();
        try {
            method = clazz.getMethod(methodName, new Class[] {});
            sb.append("[");
            if (obj != null) {
                for (int i = 0; i < obj.size(); i++) {
                    // JsonSimpleObject bean = obj.get(i);
                    // System.out.println(method.invoke(target).toString());
                    // sb.append(bean.toSimpleJSONString());
                    Object o = obj.get(i);
                    sb.append(method.invoke(o).toString());
                    if (i != (obj.size() - 1)) {
                        sb.append(",");
                    }
                }
            }
            sb.append("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String parseJsonMapList(Map<String, List<String>> map) {
        StringBuffer sb = new StringBuffer();
        if (map != null) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey().toString();
                List<String> values = (List<String>) entry.getValue();
                sb.append("{");
                sb.append("\"key\":\"" + key + "\",");
                sb.append("\"values\":" + parseJsonStringList(values));
                sb.append("},");
            }
        }
        String result = sb.toString();
        return "[" + result.substring(0, result.length() - 1) + "]";
    }

    public static String parseJsonStringList(List<String> obj) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if (obj != null) {
            for (int i = 0; i < obj.size(); i++) {
                sb.append("\"" + obj.get(i) + "\"");
                if (i != (obj.size() - 1)) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static Map<String, String> splitStringToMap(String source) {
        logger.info("splitStringToMap source=" + source);
        String[] paras = splitString(source, "$");

        if (paras == null || paras.length <= 0) {
            return null;
        }

        // here use LinkedHashMap, hot activity template apply will use first
        // key/value as primary key to identify
        // whether the record is exists.
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String para : paras) {
            logger.info("splitStringToMap para=" + para);
            String[] values = splitString(para, "|");
            if (values == null || values.length <= 1) {
                continue;
            }
            String key = values[0];
            String value = values[1];
            logger.info("splitStringToMap key=" + key);
            logger.info("splitStringToMap value=" + value);
            map.put(key, value);
        }

        return map;
    }

    public static Map<String, String> splitStringToMap(String source, String firstMark, String secondMark) {
        String[] paras = splitString(source, firstMark);

        if (paras == null || paras.length <= 0) {
            return null;
        }

        // here use LinkedHashMap, hot activity template apply will use first
        // key/value as primary key to identify
        // whether the record is exists.
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (String para : paras) {
            String[] values = splitString(para, secondMark);
            if (values == null || values.length <= 1) {
                continue;
            }
            String key = values[0];
            String value = values[1];

            map.put(key, value);
        }

        return map;
    }

    public static String[] splitString(String source, String regex) {

        if ("|".equals(regex)) {
            regex = "\\u007C";
        } else if ("$".equals(regex)) {
            regex = "\\u0024";
        } else if ("?".equals(regex)) {
            regex = "\\u003F";
        } else if ("*".equals(regex)) {
            regex = "\\u002A";
        } else if (".".equals(regex)) {
            regex = "\\u002E";
        }

        return source.split(regex);

    }

    public static List<String> matchPattern(String candidate, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(candidate);

        List<String> result = new ArrayList<String>();
        String val = null;
        while (m.find()) {
            val = m.group();
            if (!TextUtils.isEmpty(val)) {
                result.add(val);
            }
        }

        return result;
    }

    public static boolean isMatchPattern(String candidate, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(candidate);

        String val = null;
        while (m.find()) {
            val = m.group();
        }

        if (!TextUtils.isEmpty(val)) {
            return true;
        }

        return false;
    }

    public static String URLEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String URLdecode(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String stringDecode(String s) {
        try {
            return new String(s.getBytes("UTF-8"), "gb2312");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    public static boolean isSixNumber(String candidate) {
        return isMatchPattern(candidate, "^\\d{6}$");
    }

    public static String parseStringToAnimals(String str) {
        String[] animals = { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };

        int number = parseNumber(str);
        if (number <= 0 || number > 12) {
            return "";
        }

        return animals[parseNumber(str) - 1];
    }

    public static String generateCouponTicket(long total) {
        long key = total == 0 ? 100000 : total;
        return String.valueOf((long) ((1 + Math.random()) * key * 500));

    }

    public static void main(String args[]) throws Exception {
        // String oriName= "asgdkhasdlaljd_sjdajsd.jsp";
        // String suffix = oriName.substring(oriName.lastIndexOf("."));
        // System.out.println(suffix);

    }

    public static String getDataFromFile(String url) {
        logger.info("getDataFromFile:" + url);
        StringBuffer data = new StringBuffer();
        try {
            FileInputStream fstream = new FileInputStream(url);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String line = null;
            while ((line = br.readLine()) != null) {
                data.append(line.toLowerCase());
                logger.debug(line);
            }
            // Close the input stream
            in.close();
        } catch (Exception e) {// Catch exception if any
            logger.error("Error: " + e.getMessage());
        }

        return data.toString();
    }

    public static List<String> convertToList(String pg) {
        int leftIdx = 0;
        int rightIdx = 0;
        String tmp = "";
        List<String> lists = new ArrayList<String>();
        while (pg.contains("[") && pg.contains("]")) {
            leftIdx = pg.indexOf("[");
            rightIdx = pg.indexOf("]");
            tmp = pg.substring(leftIdx, rightIdx + 1);
            lists.add(tmp);
            pg = pg.substring(rightIdx + 1);
        }
        return lists;
    }

    public static String removeHtmlSpace(String str) {
        return str.replace("&nbsp;", "");
    }

}
