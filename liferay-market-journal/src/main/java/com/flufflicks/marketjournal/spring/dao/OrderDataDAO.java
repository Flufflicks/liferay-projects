package com.flufflicks.marketjournal.spring.dao;

import java.util.List;

import com.flufflicks.marketjournal.spring.model.OrderData;

public interface OrderDataDAO {
	
	void save(OrderData orderData);
	void update(OrderData orderData);
	void delete(OrderData orderData);
	OrderData findById(long id);
	List<OrderData> findAll();
}
