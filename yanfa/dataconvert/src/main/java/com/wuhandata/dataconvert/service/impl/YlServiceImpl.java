package com.wuhandata.dataconvert.service.impl;

import com.wuhandata.dataconvert.config.YlConfig;
import com.wuhandata.dataconvert.domain.Subscribe;
import com.wuhandata.dataconvert.domain.SubscribeDate;
import com.wuhandata.dataconvert.service.YlService;
import com.wuhandata.dataconvert.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YlServiceImpl implements YlService {

	@Autowired
	private YlConfig ylConfig;

	@Override
	public String listSubscribeDeptByAreaCode(String areacode) {
		return HttpUtil.ylPost(ylConfig.createUrl(ylConfig.getListSubscribeDeptByAreaCode(), "areacode=" + areacode));
	}

	@Override
	public String listSubscribeProjectByDeptID(String deptid) {
		return HttpUtil.ylPost(ylConfig.createUrl(ylConfig.getListSubscribeProjectByDeptID(), "deptid=" + deptid));
	}

	@Override
	public String listSubscribeTimeToMode3() {
		return HttpUtil.ylPost(ylConfig.createUrl(ylConfig.getListSubscribeTimeToMode3(), ""));
	}

	@Override
	public String listSubscribeDataToMode3(SubscribeDate subscribeDate) {
		return HttpUtil.ylPost(ylConfig.createUrl(ylConfig.getListSubscribeDataToMode3(), subscribeDate.toUrlParam()));
	}

	@Override
	public String addSubscribeToMode3(Subscribe subscribe) {
		return HttpUtil.ylPost(ylConfig.createUrl(ylConfig.getAddSubscribeToMode3(), subscribe.toUrlParam()));
	}

	@Override
	public String listSubscribeInfo(String areacode) {
		return HttpUtil.ylPost(ylConfig.createUrl(ylConfig.getListSubscribeInfo(), "areacode=" + areacode));
	}
}
