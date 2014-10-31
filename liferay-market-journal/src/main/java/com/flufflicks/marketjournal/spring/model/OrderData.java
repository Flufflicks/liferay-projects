package com.flufflicks.marketjournal.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ORDER_DATA", catalog = "flufflicks", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID") })
public class OrderData implements java.io.Serializable  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "INSTRUMENT")
	private String instrument;
	
	
	@Column(name = "TYPE")
	private String orderType;
	
	@Column(name = "OPEN_RPICE")
	private float openPrice;
	
	@Column(name = "CLOSE_PRICE")
	private float closePrice;
	
	@Column(name = "TP")
	private int tp;
	
	@Column(name = "SL")
	private int sl;
	
	@Column(name = "GUV")
	private int guv;
	
	@Column(name = "STRATEGY")
	private String strategy;
	
	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(final String instrument) {
		this.instrument = instrument;
	}

	public float getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(final float openPrice) {
		this.openPrice = openPrice;
	}

	public float getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(final float closePrice) {
		this.closePrice = closePrice;
	}

	public int getTp() {
		return tp;
	}

	public void setTp(final int tp) {
		this.tp = tp;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(final int sl) {
		this.sl = sl;
	}

	public int getGuv() {
		return guv;
	}

	public void setGuv(final int guv) {
		this.guv = guv;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(final String strategy) {
		this.strategy = strategy;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(final String type) {
		this.orderType = type;
	}

}
