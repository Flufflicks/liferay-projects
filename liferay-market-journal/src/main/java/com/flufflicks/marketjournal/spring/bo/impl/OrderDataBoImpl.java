package com.flufflicks.marketjournal.spring.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flufflicks.marketjournal.portal.util.VaadinPortalUtil;
import com.flufflicks.marketjournal.spring.bo.OrderDataBo;
import com.flufflicks.marketjournal.spring.dao.OrderDataDAO;
import com.flufflicks.marketjournal.spring.model.OrderData;

@Service("orderDataBo")
public class OrderDataBoImpl implements OrderDataBo {
	
	@Autowired
	OrderDataDAO dao;
	
	@Override
	public void saveOrUpdate(final OrderData orderData) {
		final long companyId = VaadinPortalUtil.getCompanyId();
		final long userId = VaadinPortalUtil.getUserId();
		orderData.setCompanyId(companyId);
		orderData.setUserId(userId);
		
		if (orderData.getId() != 0L){
			dao.update(orderData);
		} else{
			dao.save(orderData);
		}

	}

	@Override
	public void delete(final OrderData orderData) {
		dao.delete(orderData);
	}

	@Override
	public OrderData findById(final long id) {
		return dao.findById(id);
	}
	
	@Override
	public List<OrderData> findAll() {
		final long companyId = VaadinPortalUtil.getCompanyId();
		final long userId = VaadinPortalUtil.getUserId();
	
		return dao.findAllByCompanyIdAndUser(companyId,userId);
	}
		
}
