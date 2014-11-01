package com.flufflicks.marketjournal.portal.validator;

import com.vaadin.data.Validator;

public class FloatValidator implements Validator {

	@Override
	public void validate(final Object value) throws InvalidValueException {
		if (value instanceof String) {
			try {
				final String string = (String) value;
				if (string.length() > 0) {
					Float.valueOf(string);
				}
			} catch (final NumberFormatException e) {
				throw new InvalidValueException("Kein gültiger Wert. Beispiel 1.3531");
			}
		}
	}
}
