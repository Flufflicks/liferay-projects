package com.flufflicks.marketjournal.spring.bo;

import java.util.List;

import com.flufflicks.marketjournal.spring.model.OrderData;

public interface OrderDataBo {

		void save(OrderData stock);
		void update(OrderData stock);
		void delete(OrderData stock);
		List<OrderData> findAll();
		OrderData findById(long id);

}
