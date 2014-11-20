package com.flufflicks.marketjournal.spring.dao;

import java.util.List;

import com.flufflicks.marketjournal.spring.model.OrderData;

/**
 * The Interface OrderDataDAO.
 */
public interface OrderDataDAO {
	
	/**
	 * Save the order.
	 *
	 * @param orderData the order data
	 */
	void save(OrderData orderData);
	
	/**
	 * Update the order.
	 *
	 * @param orderData the order data
	 */
	void update(OrderData orderData);
	
	/**
	 * Delete the order.
	 *
	 * @param orderData the order data
	 */
	void delete(OrderData orderData);
	
	/**
	 * Find order by id.
	 *
	 * @param id the id
	 * @return the order data
	 */
	OrderData findById(long id);
	
	/**
	 * Find all orders by company id and user.
	 *
	 * @param companyId the company id
	 * @param userId the user id
	 * @return the list
	 */
	List<OrderData> findAllByCompanyIdAndUser(long companyId, long userId);
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 */
	void deleteById(long id);
	
}
