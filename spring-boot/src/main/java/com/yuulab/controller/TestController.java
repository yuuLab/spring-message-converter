package com.yuulab.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuulab.controller.request.TestJsonRequest;
import com.yuulab.controller.request.TestPlainTextRequest;
import com.yuulab.controller.response.TestJsonResponse;
import com.yuulab.controller.response.TestPlainTextResponse;

@RestController
public class TestController {

	@PostMapping(value = "/account/plaintext", produces = MediaType.TEXT_PLAIN_VALUE)
	public TestPlainTextResponse post(@RequestBody TestPlainTextRequest request) {
		System.out.println(request);
		TestPlainTextResponse response = new TestPlainTextResponse("0000000001");
		return response;
	}

	@PostMapping(value = "/account/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public TestJsonResponse post(@RequestBody TestJsonRequest request) {
		System.out.println(request);
		TestJsonResponse response = new TestJsonResponse("0000000001");
		return response;
	}

}
