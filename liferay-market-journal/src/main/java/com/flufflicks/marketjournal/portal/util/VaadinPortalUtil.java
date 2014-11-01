package com.flufflicks.marketjournal.portal.util;

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

}
