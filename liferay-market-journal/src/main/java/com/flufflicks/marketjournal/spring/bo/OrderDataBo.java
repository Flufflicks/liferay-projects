package com.flufflicks.marketjournal.spring.bo;

import java.util.List;

import com.flufflicks.marketjournal.spring.model.OrderData;

/**
 * The Interface OrderDataBo.
 */
public interface OrderDataBo {

	/**
	 * Save or update a order.
	 *
	 * @param orderData the order
	 */
	void saveOrUpdate(OrderData orderData);

	/**
	 * Delete a order.
	 *
	 * @param orderData the order
	 */
	void delete(OrderData orderData);

	/**
	 * Find all orders.
	 *
	 * @return the orders
	 */
	List<OrderData> findAll();

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the order data
	 */
	OrderData findById(long id);
	
	/**
	 * Delete a order .
	 *
	 * @param id the id
	 */
	void deleteById(long id);

}
