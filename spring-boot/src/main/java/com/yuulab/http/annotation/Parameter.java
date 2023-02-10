package com.yuulab.http.annotation;

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
public @interface Parameter {

	public enum Justified {
		/** �E���߁B*/
		RIGHT,
		/** �����߁B*/
		LEFT
	}

	/** �J�n�ʒu�B */
	int startIndex();

	/** �I���ʒu�B */
	int endIndex();

	/** �����B */
	int length();

	/** �p�f�B���O�����B�f�t�H���g�l=���p�X�y�[�X�B*/
	String paddingWith() default " ";

	/** �p�f�B���O�����B�f�t�H���g�l=�E���߁B*/
	Justified justified() default Justified.RIGHT;
}
