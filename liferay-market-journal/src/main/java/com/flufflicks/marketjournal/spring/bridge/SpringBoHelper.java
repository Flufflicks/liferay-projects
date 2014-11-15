package com.flufflicks.marketjournal.spring.bridge;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flufflicks.marketjournal.spring.bo.OrderDataBo;

/**
 * The Class SpringBoHelper. load the context and provide the OrderData
 * BussinessObject
 */
public final class SpringBoHelper {

	/**
	 * Instantiates a new spring bo helper (not used..).
	 */
	private SpringBoHelper() {

	}

	/** The Constant appContext. */
	private static final ApplicationContext APP_CONTEXT = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

	/**
	 * Gets the order data bo.
	 *
	 * @return the order data bo
	 */
	public static OrderDataBo getOrderDataBo() {
		final OrderDataBo orderDataBo = (OrderDataBo) APP_CONTEXT.getBean("orderDataBo");
		return orderDataBo;
	}

}
