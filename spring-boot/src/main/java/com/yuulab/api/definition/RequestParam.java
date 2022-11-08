package com.yuulab.api.definition;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class RequestParam {

	public Integer start;

	public Integer end;

	public String fieldName;

	public Integer fixtedLength;

}