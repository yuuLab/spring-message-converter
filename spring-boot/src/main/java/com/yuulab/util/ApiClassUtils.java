package com.yuulab.util;

public class ApiClassUtils {

	private ApiClassUtils() {
		// do nothing.
	}


	/**
	 * 指定したクラスに文字列を変換する。変換できない場合は<code>value</code>をそのまま返却する。<br>
	 * 以下型への変換をサポート。
	 * <pre>int, short, long, double, float, boolean, byte</pre>
	 * <pre>String, Integer, Short, Long, Double, Float, Boolean, Byte, BigDecimal</pre>
	 *
	 * @param clazz 変換先クラス
	 * @param value 変換対象
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
