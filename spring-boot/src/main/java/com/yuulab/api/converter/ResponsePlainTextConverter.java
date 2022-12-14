package com.yuulab.api.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.yuulab.api.definition.FixedLenghBody;
import com.yuulab.api.definition.FixedLengthParam;

public class ResponsePlainTextConverter {

	public static <TYPE extends Object> TYPE convertResponse(Class<TYPE> clazz, String body) {
		Objects.requireNonNull(clazz);
		Objects.requireNonNull(body);

		TYPE obj = null;
		try {
			Constructor<TYPE> constructor = clazz.getConstructor();
			FixedLenghBody fixedLenghBody = clazz.getAnnotation(FixedLenghBody.class);
			int totalLength = fixedLenghBody.length();
			if (totalLength != body.length()) {
				// TODO throw error
				throw new RuntimeException("total length = " + totalLength + ", body length = " + body.length());
			}
			obj = constructor.newInstance();
			for (Field field : clazz.getDeclaredFields()) {
				FixedLengthParam param = field.getAnnotation(FixedLengthParam.class);
				field.setAccessible(true);
				field.set(obj, StringUtils.substring(body, param.startIndex()-1, param.endIndex()));
			}
		} catch (Exception e) {
			// TODO システムごとにログ出力や例外処理を記述する。
			e.printStackTrace();
		}

		return obj;
	}


}
