package com.flufflicks.marketjournal.portal.util;

import java.util.Locale;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import com.liferay.portal.util.PortalUtil;
import com.vaadin.server.VaadinPortletService;

public class VaadinPortalUtil {

	public static long getCompanyId() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		// get the companyId
		final long companyId = PortalUtil.getCompanyId(req);
		return companyId;
	}

	public static long getUserId() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		// get the companyId
		final long userId = PortalUtil.getUserId(req);
		return userId;
	}

	public static Locale getCurrentLocale() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
		return req.getLocale();
	}

	public static PortletPreferences getPortletPrefs() {
		final PortletRequest req = VaadinPortletService.getCurrentPortletRequest();
        final PortletPreferences prefs = req.getPreferences();	
        return prefs;
	}

}
