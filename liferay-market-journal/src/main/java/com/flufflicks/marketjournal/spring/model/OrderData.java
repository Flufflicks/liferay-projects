package com.flufflicks.marketjournal.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The Entity Class OrderData.
 */
@Entity
@Table(name = "ORDER_DATA", catalog = "flufflicks", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID") })
public class OrderData implements java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7064455128888486147L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false)
	private long id;
	
	/** The instrument. */
	@Column(name = "INSTRUMENT")
	private String instrument;
	
	
	/** The order type. */
	@Column(name = "TYPE")
	private String orderType;
	
	/** The open price. */
	@Column(name = "OPEN_RPICE")
	private float openPrice;
	
	/** The close price. */
	@Column(name = "CLOSE_PRICE")
	private float closePrice;
	
	/** The tp. */
	@Column(name = "TP")
	private int tp;
	
	/** The sl. */
	@Column(name = "SL")
	private int sl;
	
	/** The guv. */
	@Column(name = "GUV")
	private int guv;
	
	/** The strategy. */
	@Column(name = "STRATEGY")
	private String strategy;
	
	/** The company id. */
	@Column(name = "COMPANY_ID")
	private long companyId;
	
	/** The user id. */
	@Column(name = "USER_ID")
	private long userId;
	
	/**
	 * Gets the instrument.
	 *
	 * @return the instrument
	 */
	public final String getInstrument() {
		return instrument;
	}

	/**
	 * Sets the instrument.
	 *
	 * @param value the new instrument
	 */
	public final void setInstrument(final String value) {
		this.instrument = value;
	}

	/**
	 * Gets the open price.
	 *
	 * @return the open price
	 */
	public final float getOpenPrice() {
		return openPrice;
	}

	/**
	 * Sets the open price.
	 *
	 * @param value the new open price
	 */
	public final void setOpenPrice(final float value) {
		this.openPrice = value;
	}

	/**
	 * Gets the close price.
	 *
	 * @return the close price
	 */
	public final float getClosePrice() {
		return closePrice;
	}

	/**
	 * Sets the close price.
	 *
	 * @param value the new close price
	 */
	public final void setClosePrice(final float value) {
		this.closePrice = value;
	}

	/**
	 * Gets the tp.
	 *
	 * @return the tp
	 */
	public final int getTp() {
		return tp;
	}

	/**
	 * Sets the tp.
	 *
	 * @param value the new tp
	 */
	public final void setTp(final int value) {
		this.tp = value;
	}

	/**
	 * Gets the sl.
	 *
	 * @return the sl
	 */
	public final int getSl() {
		return sl;
	}

	/**
	 * Sets the sl.
	 *
	 * @param value the new sl
	 */
	public final void setSl(final int value) {
		this.sl = value;
	}

	/**
	 * Gets the guv.
	 *
	 * @return the guv
	 */
	public final int getGuv() {
		return guv;
	}

	/**
	 * Sets the guv.
	 *
	 * @param value the new guv
	 */
	public final void setGuv(final int value) {
		this.guv = value;
	}

	/**
	 * Gets the strategy.
	 *
	 * @return the strategy
	 */
	public final String getStrategy() {
		return strategy;
	}

	/**
	 * Sets the strategy.
	 *
	 * @param value the new strategy
	 */
	public final void setStrategy(final String value) {
		this.strategy = value;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public final long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param value the new id
	 */
	public final void setId(final long value) {
		this.id = value;
	}

	/**
	 * Gets the order type.
	 *
	 * @return the order type
	 */
	public final String getOrderType() {
		return orderType;
	}

	/**
	 * Sets the order type.
	 *
	 * @param type the new order type
	 */
	public final void setOrderType(final String type) {
		this.orderType = type;
	}

	/**
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public final long getCompanyId() {
		return companyId;
	}

	/**
	 * Sets the company id.
	 *
	 * @param value the new company id
	 */
	public final void setCompanyId(final long value) {
		this.companyId = value;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public final long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param value the new user id
	 */
	public final void setUserId(final long value) {
		this.userId = value;
	}

}
