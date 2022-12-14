package com.yuulab.controller.response;

import java.io.Serializable;

import com.yuulab.api.definition.FixedLenghBody;
import com.yuulab.api.definition.FixedLengthParam;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@FixedLenghBody(length = 40)
public class TestPlainTextResponse implements FixedLengthResponse, Serializable{

	@FixedLengthParam(startIndex = 1, endIndex = 20, length = 20)
	public String accountId;

}
