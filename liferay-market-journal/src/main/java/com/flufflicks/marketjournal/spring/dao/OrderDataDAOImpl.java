package com.flufflicks.marketjournal.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flufflicks.marketjournal.spring.model.OrderData;

/**
 * The Class OrderDataDAOImpl.
 */
@Repository("orderDataDAO")
public class OrderDataDAOImpl extends CustomHibernateDaoSupport implements OrderDataDAO {

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.dao.OrderDataDAO#save(com.flufflicks.marketjournal.spring.model.OrderData)
	 */
	@Override
	@Transactional(readOnly = false)
	public final void save(final OrderData order) {
		getHibernateTemplate().save(order);
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.dao.OrderDataDAO#update(com.flufflicks.marketjournal.spring.model.OrderData)
	 */
	@Override
	@Transactional(readOnly = false)
	public final void update(final OrderData orderData) {
		getHibernateTemplate().update(orderData);

	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.dao.OrderDataDAO#delete(com.flufflicks.marketjournal.spring.model.OrderData)
	 */
	@Override
	@Transactional(readOnly = false)
	public final void delete(final OrderData orderData) {
		getHibernateTemplate().delete(orderData);
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.dao.OrderDataDAO#findById(long)
	 */
	@Override
	public final OrderData findById(final long id) {
//		final List<?> list = getHibernateTemplate().find("from OrderData where id=?", id);
//		return (OrderData) list.get(0);
		return getHibernateTemplate().get(OrderData.class, id);
	}

	/* (non-Javadoc)
	 * @see com.flufflicks.marketjournal.spring.dao.OrderDataDAO#findAllByCompanyIdAndUser(long, long)
	 */
	@Override
	public final List<OrderData> findAllByCompanyIdAndUser(final long companyId, final long userId) {
		@SuppressWarnings("unchecked")
		final List<OrderData> list = (List<OrderData>) getHibernateTemplate().find("from OrderData where companyId=? and userId= ? order by openDate", companyId, userId);

		return list;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteById(final long id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(OrderData.class, id));
	}
}
