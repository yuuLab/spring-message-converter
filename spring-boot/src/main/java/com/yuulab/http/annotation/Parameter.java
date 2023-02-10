package com.yuulab.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * リクエスト電文・レスポンス電文の固定長マッピングアノテーション。
 *
 * @author yuuLab
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {

	public enum Justified {
		/** 右埋め。*/
		RIGHT,
		/** 左埋め。*/
		LEFT
	}

	/** 開始位置。 */
	int startIndex();

	/** 終了位置。 */
	int endIndex();

	/** 長さ。 */
	int length();

	/** パディング文字。デフォルト値=半角スペース。*/
	String paddingWith() default " ";

	/** パディング方向。デフォルト値=右埋め。*/
	Justified justified() default Justified.RIGHT;
}
