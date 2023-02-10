package com.yuulab.http.converter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.tomcat.util.codec.binary.StringUtils;
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

public class PlainTextHttpMessageConverter implements HttpMessageConverter<Object> {

	private static final List<MediaType> SUPPORT_MEDIA_TYPES = Arrays.asList(MediaType.TEXT_PLAIN);

	private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

	private static final MediaType DEFAULT_MEDIA_TYPE = MediaType.TEXT_PLAIN;

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
		if (Objects.isNull(clazz) || Objects.isNull(body)) {
			return null;
		}
		return TextPlainToObjConverter.convert(clazz, body);
	}

	@Override
	public void write(Object t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		contentType = this.getContentType(contentType);
		outputMessage.getHeaders().setContentType(contentType);

		Charset charset = contentType.getCharset();
		Assert.notNull(charset, "No charset"); // should never occur

		byte[] bytes = StringUtils.getBytesUtf8(ObjToTextPlainConverter.convert(t));
		outputMessage.getHeaders().setContentLength(bytes.length);
		if (outputMessage instanceof StreamingHttpOutputMessage) {
			StreamingHttpOutputMessage streamingOutputMessage = (StreamingHttpOutputMessage) outputMessage;
			streamingOutputMessage.setBody(outputStream -> StreamUtils.copy(bytes, outputStream));
		} else {
			StreamUtils.copy(bytes, outputMessage.getBody());
		}
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
