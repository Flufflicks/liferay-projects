package com.flufflicks.marketjournal.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.dao.OrderDataDAO;
import com.flufflicks.marketjournal.spring.model.OrderData;

/**
 * The Class OrderDataBoImpl.
 * Bussiness Object for OrderData loading and creation
 */
@Service("orderDataBo")
public class OrderDataBoImpl implements OrderDataBo {
	
	/** The dao. */
	@Autowired
	private OrderDataDAO dao;
	
	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.bo.OrderDataBo#saveOrUpdate(com.flufflicks.marketjournal.spring.model.OrderData)
	 */
	@Override
	public final void saveOrUpdate(final OrderData orderData) {
		final long companyId = VaadinPortalUtil.getCompanyId();
		final long userId = VaadinPortalUtil.getUserId();
		orderData.setCompanyId(companyId);
		orderData.setUserId(userId);
		
		if (orderData.getId() != 0L) {
			dao.update(orderData);
		} else {
			dao.save(orderData);
		}

	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.bo.OrderDataBo#delete(com.flufflicks.marketjournal.spring.model.OrderData)
	 */
	@Override
	public final void delete(final OrderData orderData) {
		dao.delete(orderData);
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.bo.OrderDataBo#findById(long)
	 */
	@Override
	public final OrderData findById(final long id) {
		return dao.findById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.bo.OrderDataBo#findAll()
	 */
	@Override
	public final List<OrderData> findAll() {
		final long companyId = VaadinPortalUtil.getCompanyId();
		final long userId = VaadinPortalUtil.getUserId();
	
		return dao.findAllByCompanyIdAndUser(companyId, userId);
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.bo.OrderDataBo#deleteById(long)
	 */
	@Override
	public void deleteById(final long id) {
		dao.deleteById(id);
	}
		
}
