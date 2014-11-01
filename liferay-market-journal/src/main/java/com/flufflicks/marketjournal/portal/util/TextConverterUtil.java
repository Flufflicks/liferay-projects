package com.flufflicks.marketjournal.portal.util;

public class TextConverterUtil {

	public static float getFloatValue(final Object value) {
		if (value instanceof String) {
			return Float.valueOf((String) value);
		}
		return 0;
	}
	
	public static int getIntValue(final Object value) {
		if (value instanceof String) {
			return Integer.valueOf((String) value);
		}
		return 0;
	}

}
