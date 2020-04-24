package com.wuhandata.dataconvert.controller;

import com.wuhandata.dataconvert.domain.Subscribe;
import com.wuhandata.dataconvert.domain.SubscribeDate;
import com.wuhandata.dataconvert.service.YlService;
import com.wuhandata.dataconvert.util.YlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/yl/dataconvert")
@Api(tags = "翼龙预约数据转换")
public class YlController {

	@Autowired
	private YlService ylService;

	@GetMapping("/listSubscribeDeptByAreaCode")
	@ApiOperation(value = "获取可预约的部门")
	public String listSubscribeDeptByAreaCode(@RequestParam @ApiParam(value = "区划,必填", required = true) String areacode) {
		return YlUtil.convert(ylService.listSubscribeDeptByAreaCode(areacode));
	}

	@GetMapping("/listSubscribeProjectByDeptID")
	@ApiOperation(value = "获取可预约的事项")
	public String listSubscribeProjectByDeptID(@RequestParam @ApiParam(value = "部门id,必填" ,required = true) String deptid) {
		return YlUtil.convert(ylService.listSubscribeProjectByDeptID(deptid));
	}

	@GetMapping("/listSubscribeTimeToMode3")
	@ApiOperation(value = "获取可预约的日期")
	public String listSubscribeTimeToMode3() {
		return YlUtil.convert(ylService.listSubscribeTimeToMode3());
	}

	@PostMapping("/listSubscribeDataToMode3")
	@ApiOperation(value = "获取可预约的时间段")
	public String listSubscribeDataToMode3(@RequestBody SubscribeDate subscribeDate) {
		return YlUtil.convert(ylService.listSubscribeDataToMode3(subscribeDate));
	}

	@PostMapping("/addSubscribeToMode3")
	@ApiOperation(value = "提交预约信息")
	public String addSubscribeToMode3(@RequestBody Subscribe subscribeDate) {
		return YlUtil.convert(ylService.addSubscribeToMode3(subscribeDate));
	}

	@GetMapping("/listSubscribeInfo")
	@ApiOperation(value = "获取当天可预约事项统计")
	public String listSubscribeInfo(@RequestParam @ApiParam(value = "区划,必填,如420102",required = true) String areacode) {
		return YlUtil.convert(ylService.listSubscribeInfo(areacode));
	}
}
