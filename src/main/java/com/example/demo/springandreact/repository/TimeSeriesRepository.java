package com.example.demo.springandreact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import com.example.demo.springandreact.domain.Stock;
import com.example.demo.springandreact.domain.TimeSeries;;

@Repository
public interface TimeSeriesRepository extends JpaRepository<TimeSeries, Long> {

	List<TimeSeries> findByStock(Stock stock, Sort sort);
}