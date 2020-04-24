package com.wuhandata.dataconvert.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Subscribe {
	@ApiModelProperty("预约渠道类型,必填,默认9")
	private Integer type;
	@ApiModelProperty("人员id,非必填,可为空")
	private String prmid;
	@ApiModelProperty("事项编码,必填,传project_no")
	private String projectid;
	@ApiModelProperty("预约日期,必填,格式：yyyy-MM-dd如：2019-12-24")
	private String orderdate;
	@ApiModelProperty("预约时间段,必填,格式：HH:mm-HH:mm如：09:00-12:00")
	private String ordertime;
	@ApiModelProperty("用户身份证卡号,必填")
	private String idcard;
	@ApiModelProperty("用户姓名,必填")
	private String username;
	@ApiModelProperty("手机号码,必填")
	private String mobile;
	@ApiModelProperty("票码,非必填")
	private String ticketcode;
	@ApiModelProperty("区划,必填,如420102")
	private String areacode;

	public String toUrlParam() {
		return "type=" + type + "&prmid=" + prmid + "&projectid=" + projectid + "&orderdate=" + orderdate + "&ordertime=" + ordertime + "&idcard=" + idcard + "&username=" + username + "&mobile=" + mobile + "&ticketcode=" + ticketcode + "&areacode=" + areacode;
	}
}
