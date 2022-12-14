package com.yuulab.api.converter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

import com.yuulab.api.definition.FixedLenghBody;
import com.yuulab.api.definition.FixedLengthParam;

public class PlainTextHttpMessageConverter implements HttpMessageConverter<Object> {

	private static final List<MediaType> SUPPORT_MEDIA_TYPES = Arrays.asList(MediaType.TEXT_PLAIN);

	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.TEXT_PLAIN;

	private static final String EMPTY_VALUE = "";

	private static final String HALF_WIDTH = " ";

	private static final String FULL_WIDTH = "Å@";

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
		String body = StreamUtils.copyToString(inputMessage.getBody(), this.getCharset(inputMessage));
		System.out.println("ÅyRequest BodyÅz" + body);

		if (Objects.isNull(clazz) || Objects.isNull(body)) {
			return null;
		}
		return RequestPlainTextConverter.convertRequest(clazz, body);
	}

	@Override
	public void write(Object t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		System.out.println("ÅyResponse BodyÅz" + t.toString());

		contentType = this.getContentType(contentType);
		outputMessage.getHeaders().setContentType(contentType);

		Charset charset = contentType.getCharset();
		Assert.notNull(charset, "No charset"); // should never occur

		byte[] bytes = convertResponse(t).getBytes();
		outputMessage.getHeaders().setContentLength(bytes.length);
		if (outputMessage instanceof StreamingHttpOutputMessage) {
			StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage) outputMessage;
			streamingOutputMessage.setBody(outputStream -> StreamUtils.copy(bytes, outputStream));
		}
		else {
			StreamUtils.copy(bytes, outputMessage.getBody());
		}
	}

	private String convertResponse(Object t) {
		String res = "";
		try {

			if (t.getClass().isAnnotationPresent(FixedLenghBody.class)) {
				Class<? extends Object> clazz = t.getClass();
				FixedLenghBody fixedLenghBody = clazz.getAnnotation(FixedLenghBody.class);
				int totalLength = fixedLenghBody.length();

				StringBuilder sb = new StringBuilder();

				for (Field field : clazz.getDeclaredFields()) {
					FixedLengthParam param = field.getAnnotation(FixedLengthParam.class);
					field.setAccessible(true);
					String val = this.appendHalfWidth(field.get(t).toString(), param.length());
					sb.append(val);
				}
				res = sb.toString();
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return res;
	}


	private String appendHalfWidth(String str, int length) {
		if (Objects.isNull(str)) {
			str = "";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		for (int i = 0; i < length - str.length(); i++) {
			sb.append(HALF_WIDTH);
		}
		return sb.toString();
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

	private Charset getCharset(HttpMessage message) {
		MediaType type = message.getHeaders().getContentType();
		if (Objects.isNull(type)) {
			return DEFAULT_CHARSET;
		}
		return type.getCharset() == null ? DEFAULT_CHARSET : type.getCharset();
	}

	private MediaType getContentType(@Nullable MediaType contentType) {
		if (Objects.isNull(contentType)) {
			return DEFAULT_MEDIA_TYPE;
		}
		if (Objects.isNull(contentType.getCharset())) {
			return new MediaType(contentType, DEFAULT_CHARSET);
		}
		return contentType;
	}

}
