package com.yuulab.api.definition;

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
public @interface FixedLengthParam {

	/** start index. */
	int startIndex();

	/** end index. */
	int endIndex();

	/** length. */
	int length();
}
