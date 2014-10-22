package com.flufflicks.marketjournal.spring.bridge;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.flufflicks.marketjournal.spring.bo.OrderDataBo;

public class SpringBoHelper {

	private static final ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
	
	public static OrderDataBo getOrderDataBo(){
		final OrderDataBo orderDataBo = (OrderDataBo) appContext.getBean("orderDataBo");
		return orderDataBo;
	}

}
