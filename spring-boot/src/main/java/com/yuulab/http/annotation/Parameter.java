package com.yuulab.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * APIパラメータ定義。
 *
 * @author yuuLab
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {

  public enum Justified {
    /** 右埋め。 */
    RIGHT,
    /** 左埋め */
    LEFT
  }

  /** 開始位置。 */
  int startIndex();

  /** 終了位置。 */
  int endIndex();

  /** 長さ。 */
  int length();

  /** パディング文字。 */
  String paddingWith() default " ";

  /** パディング方向。 */
  Justified justified() default Justified.RIGHT;
}
