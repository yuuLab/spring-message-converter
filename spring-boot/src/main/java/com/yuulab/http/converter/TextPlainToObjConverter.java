package com.yuulab.http.converter;

import com.yuulab.exception.HttpMessageParseException;
import com.yuulab.http.annotation.Parameter;
import com.yuulab.util.ApiClassUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class TextPlainToObjConverter {

  public static <TYPE extends Object> TYPE convert(Class<TYPE> clazz, String body)
      throws HttpMessageParseException {
    if (Objects.isNull(clazz) || Objects.isNull(body)) {
      return null;
    }
    TYPE obj = null;
    try {
      Constructor<TYPE> constructor = clazz.getConstructor();
      obj = constructor.newInstance();
      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        field.set(obj, convertField(field, body));
      }
    } catch (Exception e) {
      throw new HttpMessageParseException("変換時エラー", e);
    }
    return obj;
  }

  private static Object convertField(Field field, String body) {
    Parameter param = field.getAnnotation(Parameter.class);
    String value = StringUtils.substring(body, param.startIndex() - 1, param.endIndex());
    return ApiClassUtils.toTargetClass(field.getClass(), value);
  }
}
