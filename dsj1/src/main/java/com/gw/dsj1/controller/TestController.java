package com.gw.dsj1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController {

	@GetMapping("/1")
	@ApiOperation("测试1")
	public String test1(@RequestParam(required = false) String name) {
		return "你好: "+name;
	}
}
