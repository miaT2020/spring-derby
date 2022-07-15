package com.example.demo.springandreact.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "stocks")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stock implements Serializable {
	
	@Id
	@GeneratedValue 
	private Long id;
	
	@NotBlank
    @Column(unique = true)
	private String ticker;
    
	private double dividend;
	private ArrayList<TimeSeries> timeSeries;
	
	@SuppressWarnings("unused")
    public Stock(){}
  
	public Stock (String ticker) {
		this.ticker = ticker;
	
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	
	public double getDivident() {
		return dividend;
	}
	
	public void setDivident(double dividend) {
		this.dividend = dividend;
	}
	
	@Override
	public String toString() {
		StringBuffer data = new StringBuffer();
		for(int i = 0; i < timeSeries.size(); i++) {
			data.append(timeSeries.get(i));
		}
		return "Ticker: " + this.ticker + "\n" +
				data;
	}

	public ArrayList<TimeSeries> getTimeSeries() {
		return timeSeries;
	}

	@OneToMany(mappedBy = "stock", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public void setTimeSeries(ArrayList<TimeSeries> timeSeries) {
		this.timeSeries = timeSeries;
	}
}

