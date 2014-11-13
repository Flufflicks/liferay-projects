package com.flufflicks.marketjournal.portal.validator;

import com.vaadin.data.Validator;

/**
 * The Class LongValidator.
 * validate string values from textfields
 */
public class LongValidator implements Validator {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2498314186611497637L;

	/* (non-Javadoc)
	 * @see com.vaadin.data.Validator#validate(java.lang.Object)
	 */
	@Override
	public final void validate(final Object value) throws InvalidValueException {
		if (value instanceof String) {
			try {
				final String string = (String) value;
				if (string.length() > 0) {
					Long.valueOf(string);
				}
			} catch (final NumberFormatException e) {
				throw new InvalidValueException("Kein gültiger Wert");
			}
		}
	}
}