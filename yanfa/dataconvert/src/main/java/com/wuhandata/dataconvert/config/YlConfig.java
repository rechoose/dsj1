package com.wuhandata.dataconvert.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("yl.config")
@Configuration
public class YlConfig {
	private String baseUrl = "/WebAPI.svc";

	private String host = "10.4.155.102";
	private int port = 9200;

	private String listSubscribeDeptByAreaCode = "/ListSubscribeDeptByAreaCode";
	private String listSubscribeProjectByDeptID = "/ListSubscribeProjectByDeptID";
	private String listSubscribeTimeToMode3 = "/ListSubscribeTimeToMode3";
	private String listSubscribeDataToMode3 = "/ListSubscribeDataToMode3";
	private String addSubscribeToMode3 = "/AddSubscribeToMode3";
	private String listSubscribeInfo = "/ListSubscribeInfo";

	public String createUrl(String apiName, String param) {
		return "http://" + host + ":" + port + baseUrl + apiName + "?" + param;

	}
}
