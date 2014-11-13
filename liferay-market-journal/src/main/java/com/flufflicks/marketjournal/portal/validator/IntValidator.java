package com.flufflicks.marketjournal.portal.validator;

import com.vaadin.data.Validator;

/**
 * The Class IntValidator.
 * validate textfield string values
 */
public class IntValidator implements Validator {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 788644618827977430L;

	/* (non-Javadoc)
	 * @see com.vaadin.data.Validator#validate(java.lang.Object)
	 */
	@Override
	public final void validate(final Object value) throws InvalidValueException {
		if (value instanceof String) {
			try {
				final String string = (String) value;
				if (string.length() > 0) {
					Integer.valueOf(string);
				}
			} catch (final NumberFormatException e) {
				throw new InvalidValueException("Kein gültiger Wert. Beispiel: 25");
			}
		}
	}

}
