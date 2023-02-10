package com.yuulab.util;

public class ApiClassUtils {

	private ApiClassUtils() {
		// do nothing.
	}


	/**
	 * �w�肵���N���X�ɕ������ϊ�����B�ϊ��ł��Ȃ��ꍇ��<code>value</code>�����̂܂ܕԋp����B<br>
	 * �ȉ��^�ւ̕ϊ����T�|�[�g�B
	 * <pre>int, short, long, double, float, boolean, byte</pre>
	 * <pre>String, Integer, Short, Long, Double, Float, Boolean, Byte, BigDecimal</pre>
	 *
	 * @param clazz �ϊ���N���X
	 * @param value �ϊ��Ώ�
	 * @return converted value
	 */
	public static Object toTargetClass(Class<?> clazz, String value) {
		if (clazz == null) return value;
		if (clazz.isPrimitive()) return toTargetPrimitiveClass(clazz, value);

		return value;
	}

	private static Object toTargetPrimitiveClass(Class<?> clazz, String value) {
		if (int.class.isAssignableFrom(clazz)) return Integer.parseInt(value);
		if (long.class.isAssignableFrom(clazz)) return Long.parseLong(value);
		if (double.class.isAssignableFrom(clazz)) return Double.parseDouble(value);
		if (short.class.isAssignableFrom(clazz)) return Short.parseShort(value);
		if (float.class.isAssignableFrom(clazz)) return Float.parseFloat(value);
		if (boolean.class.isAssignableFrom(clazz)) return Boolean.parseBoolean(value);
		if (byte.class.isAssignableFrom(clazz)) return Byte.parseByte(value);
		return value;
	}
}
