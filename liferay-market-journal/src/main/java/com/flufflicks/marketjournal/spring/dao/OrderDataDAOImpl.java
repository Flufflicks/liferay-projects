package com.flufflicks.marketjournal.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flufflicks.marketjournal.spring.model.OrderData;

@Repository("orderDataDAO")
public class OrderDataDAOImpl extends CustomHibernateDaoSupport implements OrderDataDAO {

	@Override
	@Transactional(readOnly = false)
	public void save(final OrderData order) {
		getHibernateTemplate().save(order);
	}

	@Override
	public void update(final OrderData orderData) {
		getHibernateTemplate().update(orderData);

	}

	@Override
	public void delete(final OrderData orderData) {
		getHibernateTemplate().delete(orderData);
	}

	@Override
	public OrderData findById(final long id) {
		final List list = getHibernateTemplate().find("from OrderData where id=?", id);
		return (OrderData) list.get(0);
	}

	@Override
	public List<OrderData> findAll() {
		@SuppressWarnings("unchecked")
		final List<OrderData> list = (List<OrderData>) getHibernateTemplate().find("from OrderData ");

		return list;
	}
}
