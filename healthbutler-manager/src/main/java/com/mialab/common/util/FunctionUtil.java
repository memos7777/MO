package com.mialab.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

public class FunctionUtil {

	static Logger logger = Logger.getLogger(FunctionUtil.class.getName());

	public static String EncoderByMd5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			logger.warn(e.toString());
		}
		// BASE64Encoder base64en = new BASE64Encoder();
		md5.update(str.getBytes());
		String strDes = bytes2Hex(md5.digest()); // to HexString
		return strDes;
	}

	public static String upperCaseEncoderByMd5(String str) {
		return EncoderByMd5(str).toUpperCase();
	}

	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;

		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static String getRequestTime() {
		SimpleDateFormat sdb = new SimpleDateFormat("yyyyMMddhhmmss", Locale.US);
		return sdb.format(new Date());
	}

	public static long getCurrentDateTime() {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		String dateTime = df.format(new Date());
		Date d = null;
		try {
			d = df.parse(dateTime);
		} catch (Exception e) {
			return new Date().getTime();
		}
		return d.getTime();
	}

	public static String getCurrentDateTimeWithoutMill() {
		SimpleDateFormat sdb = new SimpleDateFormat("HH:mm", Locale.US);
		return sdb.format(new Date());
	}

	public static String getCurrentDateWithoutMill() {
		SimpleDateFormat sdb = new SimpleDateFormat("yyyyMMddHHmm", Locale.CHINESE);
		return sdb.format(new Date());
	}

	public static String getCurrentTime() {
		SimpleDateFormat sdb = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.US);
		return sdb.format(new Date());
	}

	public static Date parseTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("H:m:s", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseDateWithoutSeconds(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm", Locale.CHINESE);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
			return null;
		}
	}

	public static Date parseDateTimeWithSeconds(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
			return null;
		}
	}

	public static Date parseBookTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm", Locale.CHINESE);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parsePushTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseDateTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseMovieTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d H:m", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseMovieDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseUserDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseParaDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static Date parseLotteryDate(String str) {
		return parseMovieDate(str);
	}

	public static String formatTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			logger.warn(e.toString());
		}
		return "null";
	}

	public static String getDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getParaDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getParaDateTimeString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getStandDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getMovieDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getDateString1(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date == null ? new Date() : date);
	}

	public static String getDateTimeString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getTimeString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getParaTimeString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getTimeFormatString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
		String time = sdf.format(date == null ? new Date() : date);
		return "00:00".equals(time) ? "-" : time;
	}

	public static String getYearMonthString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月", Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getDateString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		return sdf.format(date == null ? new Date() : date);
	}

	public static Date parseChineseDate(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINESE);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static String getChineseDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日 E", Locale.CHINESE);
		return sdf.format(date == null ? new Date() : date);
	}

	public static String getFullDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd E", Locale.CHINESE);
		return sdf.format(date == null ? new Date() : date);
	}

	public static boolean isToday(Date date) {
		if (date == null) {
			return false;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar today = Calendar.getInstance();

		if (cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
			return true;
		}
		return false;

	}

	public static boolean isBeforeToday(Date date) {
		if (date == null) {
			return false;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar today = Calendar.getInstance();

		if (cal.get(Calendar.DAY_OF_YEAR) < today.get(Calendar.DAY_OF_YEAR)) {
			return true;
		}
		return false;

	}

	public static String formatNumberStr(String str) {
		if (str == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int chr = str.charAt(i);
			if (chr >= 0x30 && chr <= 0x39) {
				sb.append((char) chr);
			}
		}
		return sb.toString();
	}

	public static boolean isMobileNumber(String str) {
		String s = formatNumberStr(str);
		if (s.length() == 11 && s.startsWith("1")) {
			return true;
		}
		return false;
	}

	public static boolean isOverTime(Date date, int minute) {
		if (date == null) {
			return true;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar now = Calendar.getInstance();

		if ((now.getTimeInMillis() - date.getTime()) > minute * 60 * 1000) {
			return true;
		}
		return false;
	}

	public static Calendar getCalendarByTime(int hour, int minute) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, minute);
		return c;
	}

	/*
	 * 格式为:11:30
	 */
	public static boolean compareTimeWithoutMill(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("HH:mm");
		boolean f = false;
		try {
			f = compareDate(df.parse(time1), df.parse(time2));
		} catch (Exception e) {

		}
		return f;
	}

	public static boolean compareDate(Date date1, Date date2) {
		try {
			if (date1.getTime() >= date2.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.warn(e.toString());
		}

		return false;
	}

	public static boolean compareDate(Calendar date1, Calendar date2) {
		try {
			if (date1.getTimeInMillis() >= date2.getTimeInMillis()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.warn(e.toString());
		}

		return false;
	}

	public static boolean isOverTime(long date1, long date2, int minutes) {
		try {
			if ((date1 - date2) > (1000 * 60 * minutes)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.warn(e.toString());
		}

		return false;
	}

	public static boolean isBetweenDate(Date startDate, Date endDate) {
		Date nowDate = new Date();
		if (compareDate(nowDate, startDate) && compareDate(endDate, nowDate)) {
			return true;
		}

		return false;
	}

	public static boolean isBetweenDate(Calendar startDate, Calendar endDate) {
		Calendar nowDate = Calendar.getInstance();
		if (compareDate(nowDate, startDate) && compareDate(endDate, nowDate)) {
			return true;
		}

		return false;
	}

	public static Date addDate(Date d, long day) {
		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);
	}

	/**
	 * 
	 * @param localVersion
	 *            String
	 * @param serverVersion
	 *            String
	 * @return 0 = same, -1 = version1 newer than version2, 1 = version2 old
	 *         than version1, -2 unknown
	 */
	public static int checkVersion(String version1, String version2) {
		try {
			if (TextUtils.isEmpty(version1) || TextUtils.isEmpty(version2)) {
				return -2;
			}
			String[] localVs = version1.split("\\.");
			String[] serverVs = version2.split("\\.");

			int index = Math.min(localVs.length, serverVs.length);
			for (int i = 0; i < index; i++) {
				if (getInt(localVs[i]) == getInt(serverVs[i])) {
					continue;
				} else if (getInt(localVs[i]) < getInt(serverVs[i])) {
					return 1;
				} else {
					return -1;
				}
			}
		} catch (Exception e) {
			return 1;
		}
		return 0;
	}

	/**
	 * 
	 * @param mobileNumber
	 * 
	 * @return -1-不是电话号码， 0-新电信， 1-新移动， 2-新联通, 3-未知号码
	 */
	public static int checkVendor(String mobileNumber) {
		if (!isMobileNumber(mobileNumber)) {
			return -1;
		}

		List<String> telecom = Arrays.asList(new String[] { "133", "153", "189", "180", "181" });
		List<String> mobile = Arrays.asList(new String[] { "134", "135", "136", "137", "138", "139", "147", "150", "151", "152", "157", "158", "159",
				"182", "183", "187", "188" });
		List<String> unicom = Arrays.asList(new String[] { "130", "131", "132", "145", "155", "156", "185", "186" });

		String header = mobileNumber.substring(0, 3);
		if (telecom.contains(header)) {
			return 0;
		} else if (mobile.contains(header)) {
			return 1;
		} else if (unicom.contains(header)) {
			return 2;
		} else {
			return 3;
		}
	}

	public static Calendar getNextDayOffset(int offset) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, offset);
		return cal;
	}

	public static Calendar getNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 2);
		return cal;
	}

	public static Calendar getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		return cal;
	}

	public static int getInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return 0;
		}
	}

	public static String getRandomCode(int length) {
		String randomCode = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String rand = String.valueOf(random.nextInt(10));
			randomCode += rand;
		}
		return randomCode;
	}

	/**
	 * 根据经纬度判断距离
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * 6378137;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static Calendar getMonthFirstDate(Calendar today) {
		Calendar firstDate = Calendar.getInstance();
		firstDate.setTime(today.getTime());
		firstDate.set(Calendar.DAY_OF_MONTH, 1);
		while (firstDate.get(Calendar.DAY_OF_WEEK) > 1) {
			firstDate.add(Calendar.DAY_OF_WEEK, -1);
		}
		return firstDate;
	}

	public static Calendar getMonthLastDate(Calendar today) {
		Calendar firstDate = getMonthFirstDate(today);
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(firstDate.getTime());
		lastDate.add(Calendar.DAY_OF_YEAR, 41);
		lastDate.set(Calendar.HOUR, 0);
		lastDate.set(Calendar.MINUTE, 0);
		lastDate.set(Calendar.SECOND, 0);
		return lastDate;
	}

	/**
	 * 功能：检测当前URL是否可连接或是否有效, 描述：最多连接网络 3 次, 如果 3 次都不成功，视为该地址不可用
	 * 
	 * @param urlStr
	 *            指定URL网络地址
	 * @return URL
	 */
	public synchronized static boolean isConnect(String urlStr) {
		int counts = 0;
		boolean flag = false;
		if (urlStr == null || urlStr.length() <= 0) {
			return false;
		}
		while (counts < 1) {
			long start = 0;
			try {
				URL url = new URL(urlStr);
				start = System.currentTimeMillis();
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setConnectTimeout(5 * 1000);
				int state = con.getResponseCode();
				logger.debug("请求断开的URL一次需要：" + (System.currentTimeMillis() - start) + "毫秒");
				if (state == 200) {
					flag = true;
					logger.info(urlStr + "--可用");
				}
				break;
			} catch (Exception ex) {
				counts++;
				logger.debug("请求断开的URL一次需要：" + (System.currentTimeMillis() - start) + "毫秒");
				logger.debug("连接第 " + counts + " 次，" + urlStr + "--不可用");
				continue;
			}
		}
		return flag;
	}

	public static synchronized long generateTradeNo() {
		long head = Calendar.getInstance().getTimeInMillis();
		String id = String.valueOf(head);
		while (id.length() < 18) {
			id += (long) (1024 * Math.random());
		}
		return Long.parseLong(id.substring(0, 18));
	}

	public static Date parseDateWithTime(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.warn(e.toString());
		}
		return new Date();
	}

	public static String getCurrentDate1() {
		SimpleDateFormat sdb = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
		return sdb.format(new Date());
	}

	public static String getCurrentDate() {
		SimpleDateFormat sdb = new SimpleDateFormat("yyyy年M月d日", Locale.CHINESE);
		return sdb.format(new Date());
	}

	public static Date convertMillisecondsToDate(long milliseconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		return calendar.getTime();
	}

	public static String decodeName(String accountName) {
		if (accountName == null) {
			return null;
		}
		try {
			return new String(accountName.getBytes("ISO8859-1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		return "";
	}

	/**	 
	 * 
	 * @param inputString
	 * @param num
	 * @param tf
	 * @return
	 */
	public static String md5hashString(String inputString, int num, Boolean tf) {
		if (tf == true) {
			for (int i = 0; i < num; i++) {
				inputString = FunctionUtil.upperCaseEncoderByMd5(inputString);
			}
		} else {
			for (int i = 0; i < num; i++) {
				if (i == 0) {
					inputString = FunctionUtil.upperCaseEncoderByMd5(inputString);

					inputString = inputString.substring(20, 25) + inputString.substring(5, 20);
				}
				if (i == 1) {
					inputString = FunctionUtil.upperCaseEncoderByMd5(inputString);

					inputString = inputString.substring(5, 20) + inputString.substring(20, 25);
				}
				if (i == 2) {
					inputString = FunctionUtil.upperCaseEncoderByMd5(inputString);
					inputString = inputString.substring(20, 25) + inputString.substring(5, 20);
				}
				if (i > 2) {
					inputString = FunctionUtil.upperCaseEncoderByMd5(inputString).substring(8, 28);
				}
			}
			inputString = FunctionUtil.upperCaseEncoderByMd5(inputString);
		}
		return inputString;
	}

	/**
	 * json字符串转换成java对象
	 * 
	 * @param json
	 * @param c
	 * @return
	 */
	public static <T> T jsonToObject(String json, Class<T> c) {
		T o = null;
		try {
			o = new ObjectMapper().readValue(json, c);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return o;
	}

	public static <T> List<T> jsonToList(String jsonVal, Class<?> clazz) {
		List<T> list = null;
		try {
			TypeFactory t = TypeFactory.defaultInstance();
			// 指定容器结构和类型（这里是ArrayList和clazz）
			list = new ObjectMapper().readValue(jsonVal, t.constructCollectionType(ArrayList.class, clazz));
			// 如果T确定的情况下可以使用下面的方法：
			// List<T> list = objectMapper.readValue(jsonVal, new
			// TypeReference<List<T>>() {});
		} catch (IOException e) {
			logger.error(e.toString());
		}
		return list;
	}

	public static String toJson(Object object) {

		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	public static String formatMacDeviceId(String deviceId) {
		if (deviceId == null) {
			return "FREY0123456789";
		}
		return deviceId.replaceAll("\\.", "");
	}

	public static String getShortUrl(String url) {
		HttpURLConnection connection = null;
		InputStream in = null;
		BufferedReader reader = null;
		String jsonUrl;
		try {
			logger.debug(url);
			connection = (HttpURLConnection) new URL("http://raiyi.cn/shorturlapi?oriurl=" + URLEncoder.encode(url, "UTF-8")).openConnection();
			connection.setConnectTimeout(10 * 1000);
			in = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = null;
			StringBuffer buffer = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			jsonUrl = buffer.toString();

			logger.debug(jsonUrl);
			JsonFactory f = new JsonFactory();
			JsonParser jp = f.createJsonParser(jsonUrl);

			while (jp.nextToken() != JsonToken.END_OBJECT) {
				String namefield = jp.getCurrentName();
				if ("shorturl".equals(namefield)) {
					logger.debug(jp.getText().trim());
					return jp.getText().trim();
				}
				jp.nextToken();
			}
		} catch (Exception e) {
			return "";
		}
		return "";
	}

}
