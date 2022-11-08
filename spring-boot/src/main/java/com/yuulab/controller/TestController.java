package com.yuulab.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuulab.controller.request.TestRequest;

@RestController
public class TestController {

	@PostMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
	public String post(@RequestBody TestRequest body) {
		System.out.println(body);
		return body.toString();
	}

}
