package com.yuulab.config;

import java.io.IOException;
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

public class PlainTextHttpMessageConverter implements HttpMessageConverter<Object> {
  
  private static final List<MediaType> SUPPORT_MEDIA_TYPES = Arrays.asList(
      MediaType.TEXT_PLAIN, MediaType.valueOf("text/plain;charset=UTF-8"));

  @Override
  public boolean canRead(Class<?> clazz, @Nullable MediaType mediaType) {
    System.out.println("can read");
    System.out.println(mediaType);
    return isSupportMediaType(mediaType);
//    return true;
  }

  @Override
  public boolean canWrite(Class<?> clazz, @Nullable MediaType mediaType) {
    System.out.println("can write");
    System.out.println(mediaType);
    return isSupportMediaType(mediaType);
  }

  @Override
  public List<MediaType> getSupportedMediaTypes() {
    return SUPPORT_MEDIA_TYPES;
  }

  @Override
  public Object read(Class<? extends Object> clazz, HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {
    System.out.println("read");
    System.out.println(clazz);
    return "read read read";
  }

  @Override
  public void write(Object t, @Nullable MediaType contentType, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {
    System.out.println(t.toString());
  }
  
  private boolean isSupportMediaType(MediaType mediaType) {
    if (Objects.isNull(mediaType)) {
      return true;
    }
    for (MediaType supportType : SUPPORT_MEDIA_TYPES) {
      if (Objects.equals(mediaType, supportType)) {
        return true;
      }
    }
    return false;
  }
  
}
