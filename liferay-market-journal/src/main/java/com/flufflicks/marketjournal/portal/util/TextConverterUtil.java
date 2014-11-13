package com.flufflicks.marketjournal.portal.util;

/**
 * The Class TextConverterUtil. convert float and int values from string values
 */
public final class TextConverterUtil {

	/**
	 * Instantiates a new text converter util (not used...).
	 */
	private TextConverterUtil() {

	}

	/**
	 * Gets float value from string.
	 *
	 * @param value
	 *            the value
	 * @return the float value
	 */
	public static float getFloatValue(final Object value) {
		if (value instanceof String) {
			return Float.valueOf((String) value);
		}
		return 0;
	}

	/**
	 * Gets int value from string.
	 *
	 * @param value
	 *            the value
	 * @return the int value
	 */
	public static int getIntValue(final Object value) {
		if (value instanceof String) {
			return Integer.valueOf((String) value);
		}
		return 0;
	}

}
