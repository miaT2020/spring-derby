package com.example.demo.springandreact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.springandreact.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

	  Stock findByTicker(String ticker);
	  void deleteByTicker(String ticker);
}