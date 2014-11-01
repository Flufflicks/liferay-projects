package com.flufflicks.marketjournal.spring.bo;

import java.util.List;

import com.flufflicks.marketjournal.spring.model.OrderData;

public interface OrderDataBo {

		void saveOrUpdate(OrderData orderData);
		void delete(OrderData orderData);
		List<OrderData> findAll();
		OrderData findById(long id);

}
