package com.flufflicks.marketjournal.portal.util;

import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import com.liferay.portal.util.PortalUtil;
import com.vaadin.server.VaadinPortletService;

// TODO: Auto-generated Javadoc
/**
 * The Class VaadinPortalUtil. to get liferay and portal specific values
 */
public final class VaadinPortalUtil {

	/**
	 * Instantiates a new vaadin portal util (not used - static util...).
	 */
	private VaadinPortalUtil() {

	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public static long getCompanyId() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		// get the companyId
		final long companyId = PortalUtil.getCompanyId(req);
		return companyId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public static long getUserId() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		// get the companyId
		final long userId = PortalUtil.getUserId(req);
		return userId;
	}

	/**
	 * Gets the current locale.
	 *
	 * @return the current locale
	 */
	public static Locale getCurrentLocale() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		return req.getLocale();
	}

	/**
	 * Gets the portlet prefs.
	 *
	 * @return the portlet prefs
	 */
	public static PortletPreferences getPortletPrefs() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		final PortletPreferences prefs = req.getPreferences();
		return prefs;
	}

}
