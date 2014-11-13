package com.flufflicks.marketjournal.portal.presenter;

/**
 * The Interface Presenter. The presenter is used to implement the view logic
 * and collect data which is displayed by the view
 */
public interface Presenter {

	/**
	 * The bind method.
	 * for binding the view with the presenter (fetch the data for view and add used listener)
	 */
	void bind();

	/**
	 * Unbind.
	 * for unbind the view from the presenter (remove listener ...)
	 */
	void unbind();

}
