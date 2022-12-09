package com.yuulab.controller.request;

import java.io.Serializable;

import com.yuulab.api.definition.FixedLenghBody;
import com.yuulab.api.definition.FixedLengthParam;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@FixedLenghBody(length = 40)
public class TestRequest implements Serializable {

	@FixedLengthParam(startIndex = 1, endIndex = 10, length = 10)
	public String firstName;

	@FixedLengthParam(startIndex = 11, endIndex = 20, length = 10)
	public String lastName;

	@FixedLengthParam(startIndex = 21, endIndex = 31, length = 11)
	public String phoneNumber;

	@FixedLengthParam(startIndex = 32, endIndex = 33, length = 2)
	public String age;

}
