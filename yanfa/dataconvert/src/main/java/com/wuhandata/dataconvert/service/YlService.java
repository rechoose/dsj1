package com.wuhandata.dataconvert.service;

import com.wuhandata.dataconvert.domain.Subscribe;
import com.wuhandata.dataconvert.domain.SubscribeDate;

public interface YlService {

	String listSubscribeDeptByAreaCode(String areacode);

	String listSubscribeProjectByDeptID(String deptid);

	String listSubscribeTimeToMode3();

	/**
	 * 获取可预约的时间段
	 *
	 * @param subscribeDate
	 * @return 调用失败无数据返回：空字符串
	 */
	String listSubscribeDataToMode3(SubscribeDate subscribeDate);

	/**
	 * 提交预约信息
	 *
	 * @param subscribe 预约信息
	 * @return 预约成功返回预约ID;调用失败返回值：0 表示预约失败 -1 表示预约已满  -2表示服务器错误   -3表示已预约 -4表示已拉黑
	 */
	String addSubscribeToMode3(Subscribe subscribe);

	/**
	 * 获取当天可预约事项统计
	 *
	 * @param areacode 区划 必填
	 * @return 调用失败无数据返回：空字符串
	 */
	String listSubscribeInfo(String areacode);
}
