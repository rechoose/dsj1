package com.wuhandata.dataconvert.util;

import org.apache.commons.lang.StringEscapeUtils;

public class YlUtil {

	public static String convert(String origin) {
		String s = StringEscapeUtils.unescapeJava(origin);
		if (s.startsWith("\"") && s.lastIndexOf("\"") == (s.length() - 1)) {
			 s = s.substring(1, s.length() - 1);
		}
		return s;
	}


	public static void main(String[] args) {
//		String s = "\"{\\\"SubscribeDate\\\":[{\\\"datas\\\":\\\"2020-04-24\\\"},{\\\"datas\\\":\\\"2020-04-26\\\"},{\\\"datas\\\":\\\"2020-04-27\\\"},{\\\"datas\\\":\\\"2020-04-28\\\"},{\\\"datas\\\":\\\"2020-04-29\\\"},{\\\"datas\\\":\\\"2020-04-30\\\"},{\\\"datas\\\":\\\"2020-05-06\\\"}]}\"";
		String s = "123123";
		convert(s);
	}
}
