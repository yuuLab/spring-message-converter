package com.yuulab.controller.response;

import java.io.Serializable;

import com.yuulab.http.annotation.Parameter;
import com.yuulab.http.annotation.Parameter.Justified;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TestPlainTextResponse implements Serializable {

	@Parameter(startIndex = 1, endIndex = 10, length = 10, paddingWith = " ", justified = Justified.RIGHT)
	private String accountId;

	@Parameter(startIndex = 21, endIndex = 40, length = 20, paddingWith = "x", justified = Justified.LEFT)
	private String transactionId;

	@Parameter(startIndex = 41, endIndex = 50, length = 10, paddingWith = "0", justified = Justified.LEFT)
	private Long amount;

}
