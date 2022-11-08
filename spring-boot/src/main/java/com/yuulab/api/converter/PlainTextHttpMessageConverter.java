package com.yuulab.api.converter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

public class PlainTextHttpMessageConverter implements HttpMessageConverter<Object> {

	private static final List<MediaType> SUPPORT_MEDIA_TYPES = Arrays.asList(MediaType.TEXT_PLAIN);

	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

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

	private Charset getCharset(HttpInputMessage inputMessage) {
		MediaType type = inputMessage.getHeaders().getContentType();
		if (Objects.isNull(type)) {
			return DEFAULT_CHARSET;
		}
		return type.getCharset() == null ? DEFAULT_CHARSET : type.getCharset();
	}

}
