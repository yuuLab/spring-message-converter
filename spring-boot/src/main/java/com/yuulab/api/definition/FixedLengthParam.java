package com.yuulab.api.definition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���N�G�X�g�d���E���X�|���X�d���̌Œ蒷�}�b�s���O�A�m�e�[�V�����B
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
