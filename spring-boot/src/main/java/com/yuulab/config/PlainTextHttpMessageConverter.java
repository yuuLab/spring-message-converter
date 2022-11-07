package com.yuulab.config;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import com.yuulab.api.definition.ApiDefinitionsPool;
import com.yuulab.api.definition.RequestParam;
import com.yuulab.controller.request.TestRequest;

public class PlainTextHttpMessageConverter implements HttpMessageConverter<Object> {

	private static final List<MediaType> SUPPORT_MEDIA_TYPES = Arrays.asList(MediaType.TEXT_PLAIN);

	@Override
	public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
		return isSupportMediaType(mediaType);
	}

	@Override
	public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
		return isSupportMediaType(mediaType);
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return SUPPORT_MEDIA_TYPES;
	}

	@Override
	public Object read(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		TestRequest request = new TestRequest();
		request.firstName = "test";
		request.lastName = "test";

		System.out.println(clazz.getName());

		return this.createRequestClass(clazz, inputMessage);
	}

	private Object createRequestClass(Class<? extends Object> clazz, HttpInputMessage inputMessage) {
		System.out.println("=====================START=====================");
		String body = null;
		try {

			body = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
			List<RequestParam> params = ApiDefinitionsPool.getRequestParams().get(clazz.getName());
			for (Field field : clazz.getDeclaredFields()) {
				for (RequestParam param : params) {
					if (Objects.equals(param.fieldName, field.getName())) {
						System.out.println(param.fieldName);
					}
				}

			}

		} catch (IOException e) {
			// TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ catch ÉuÉçÉbÉN
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(body)) {
			return "";
		}
		System.out.println("=====================END=====================");
		return new TestRequest();
	}

	@Override
	public void write(Object t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
	}

	private boolean isSupportMediaType(MediaType mediaType) {
		if (Objects.isNull(mediaType)) {
			return true;
		}
		for (MediaType supportType : SUPPORT_MEDIA_TYPES) {
			if (supportType.includes(mediaType)) {
				return true;
			}
		}
		return false;
	}

}
