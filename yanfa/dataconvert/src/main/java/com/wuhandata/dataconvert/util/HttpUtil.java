package com.wuhandata.dataconvert.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpUtil {

	public static String ylPost(String urlStr) {
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse httpResponse = null;
		BufferedReader reader = null;
		StringBuilder result = null;
		HttpRequestBase httpRequest = null;
		try {
			httpClient = HttpClientBuilder.create().build();
			httpRequest = new HttpPost(urlStr);

			httpRequest.setHeader("accept", "*/*");
			httpRequest.setHeader("Content-Type", "application/json;charset=utf-8");
			httpRequest.setHeader("connection", "Keep-Alive");
			httpRequest.setHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			httpResponse = httpClient.execute(httpRequest);
			Header[] allHeaders = httpResponse.getAllHeaders();
			HttpEntity entity = httpResponse.getEntity();
			reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
			String line;
			result = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			return result.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpResponse != null) {
				try {
					httpResponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpClient != null) {
				try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "";
	}

}
