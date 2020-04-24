package com.wuhandata.dataconvert.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@Api(tags = "api")
public class TestController {

	@GetMapping("")
	public String test1(@RequestParam String name) {
		return "test: " + name;
	}

	@PostMapping("/1")
	public String test1(@RequestParam String name, @RequestParam String code) {
		return "test: " + name + "  " + code;
	}
}
