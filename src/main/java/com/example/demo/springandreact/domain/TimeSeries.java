package com.example.demo.springandreact.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.ArrayList;


@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TimeSeries implements  Serializable{
	

	@Id
    @GeneratedValue
	private long id;
	
	private String time;
	private double openPrice;
	private double closePrice;
	private double highPrice;
	private double lowPrice;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    private Stock stock;
	
	 @SuppressWarnings("unused")
	  public TimeSeries(){}
	 
	
	/**
	 * Constructor of a time series.
	 * 
	 * @param time		The representation of the date.
	 * @param open		The opening value of the date
	 * @param close		The value at the closing.
	 * @param high
	 * @param low
	 */
	public TimeSeries (String time, double open, double close, double high, double low, Stock stock) {
		this.setTime(time);
		this.setOpenPrice(open);
		this.setClosePrice(close);
		this.setHighPrice(high);
		this.setLowPrice(low);
		this.stock = stock;
	}
	
	/**
	 * Constructor of a time series.
	 * 
	 * @param time		The representation of the date.
	 * @param open		The opening value of the date
	 * @param close		The value at the closing.
	 * @param high
	 * @param low
	 */
	public TimeSeries (String time, double open, double close, double high, double low) {
		this.setTime(time);
		this.setOpenPrice(open);
		this.setClosePrice(close);
		this.setHighPrice(high);
		this.setLowPrice(low);
		
	}



	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public double getOpenPrice() {
		return openPrice;
	}


	public void setOpenPrice(double open) {
		this.openPrice = open;
	}


	public double getClosePrice() {
		return closePrice;
	}


	public void setClosePrice(double close) {
		this.closePrice = close;
	}


	public double getHighPrice() {
		return highPrice;
	}


	public void setHighPrice(double high) {
		this.highPrice = high;
	}


	public double getLowPrice() {
		return lowPrice;
	}


	public void setLowPrice(double low) {
		this.lowPrice = low;
	}
	

	public Stock getStock() {
		return this.stock;
	}
	
	public long getId() {
		return this.id;
	}
}
