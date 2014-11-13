package com.flufflicks.marketjournal.portal.validator;

import com.vaadin.data.Validator;

/**
 * The Class FloatValidator.
 */
public class FloatValidator implements Validator {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1619609240038571088L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Validator#validate(java.lang.Object)
	 */
	@Override
	public final void validate(final Object value) throws InvalidValueException {
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
