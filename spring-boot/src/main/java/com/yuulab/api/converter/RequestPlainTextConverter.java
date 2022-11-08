package com.yuulab.api.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.yuulab.api.definition.ApiDefinitionsPool;
import com.yuulab.api.definition.RequestParam;

public class RequestPlainTextConverter {

	public static <TYPE extends Object> TYPE convertRequest(Class<TYPE> clazz, String body) {
		Objects.requireNonNull(clazz);
		Objects.requireNonNull(body);

		TYPE obj = null;
		try {
			Constructor<TYPE> constructor = clazz.getConstructor();
			obj = constructor.newInstance();

			List<RequestParam> params = ApiDefinitionsPool.getRequestParams().get(clazz.getName());
			for (Field field : clazz.getDeclaredFields()) {
				for (RequestParam param : params) {
					if (Objects.equals(param.fieldName, field.getName())) {
						field.setAccessible(true);
						field.set(obj, StringUtils.substring(body, param.start - 1, param.end));
					}
				}
			}
		} catch (Exception e) {
			// TODO システムごとにログ出力や例外処理を記述する。
			e.printStackTrace();
		}

		return obj;
	}

}
