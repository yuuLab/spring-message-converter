package com.yuulab.http.converter;

import com.yuulab.exception.HttpMessageParseException;
import com.yuulab.http.annotation.Parameter;
import com.yuulab.http.annotation.Parameter.Justified;
import java.lang.reflect.Field;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

public class ObjToTextPlainConverter {

  public static String convert(Object httpBody) {
    if (Objects.isNull(httpBody)) {
      return "";
    }
    try {
      Class<? extends Object> clazz = httpBody.getClass();
      StringBuilder sb = new StringBuilder();
      for (Field field : clazz.getDeclaredFields()) {
        field.setAccessible(true);
        String val = convertField(field, httpBody);
        sb.append(val);
      }
      return sb.toString();
    } catch (ReflectiveOperationException e) {
      e.printStackTrace();
      throw new HttpMessageParseException("変換時エラー", e);
    }
  }

  private static String convertField(Field field, Object obj) throws ReflectiveOperationException {
    String value = Objects.isNull(field.get(obj)) ? "" : field.get(obj).toString();
    return padding(value, field.getAnnotation(Parameter.class));
  }

  private static String padding(String value, Parameter paramAnno) {
    Justified direction = paramAnno.justified();
    if (direction == Justified.RIGHT) {
      return StringUtils.rightPad(value, paramAnno.length(), paramAnno.paddingWith());
    }
    return StringUtils.leftPad(value, paramAnno.length(), paramAnno.paddingWith());
  }
}
