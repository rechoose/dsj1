package com.wuhandata.dataconvert.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SubscribeDate {

	@ApiModelProperty("日期,yyyy-MM-dd,必填")
	private String stime;
	@ApiModelProperty("事项编码,必填")
	private String projectno;
	@ApiModelProperty("区划,必填")
	private String areacode;

	public String toUrlParam() {
		return "stime=" + stime + "&projectno=" + projectno + "&areacode=" + areacode;
	}
}
