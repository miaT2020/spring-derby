package com.example.demo.springandreact.service;

import java.util.List;
import java.util.Optional;


import com.example.demo.springandreact.domain.TimeSeries;

public interface ITimeSeriesService {
    Iterable<TimeSeries> list();
    
    TimeSeries create(TimeSeries timesereis);
    
    Optional<TimeSeries> read(long id);
    
    
    void delete(long id);

	List<TimeSeries> load(String[] tickers);
}