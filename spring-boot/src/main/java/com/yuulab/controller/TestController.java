package com.yuulab.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuulab.controller.request.TestPlainTextRequest;
import com.yuulab.controller.response.TestPlainTextResponse;

@RestController
public class TestController {

	@PostMapping(value = "/account/plaintext", produces = MediaType.TEXT_PLAIN_VALUE)
	public TestPlainTextResponse post(@RequestBody TestPlainTextRequest request) {
		TestPlainTextResponse response = new TestPlainTextResponse("00001", "ABC12345", 9999L);
		return response;
	}
}
