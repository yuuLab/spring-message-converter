package com.yuulab.controller.request;

import java.io.Serializable;

import com.yuulab.http.annotation.Parameter;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class TestPlainTextRequest implements Serializable {

	@Parameter(startIndex = 1, endIndex = 10, length = 10)
	public String firstName;

	@Parameter(startIndex = 11, endIndex = 20, length = 10)
	public String lastName;

	@Parameter(startIndex = 21, endIndex = 31, length = 11)
	public String phoneNumber;

	@Parameter(startIndex = 32, endIndex = 33, length = 2)
	public String age;

}
