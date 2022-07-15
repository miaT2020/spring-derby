package com.example.demo.springandreact.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.springandreact.domain.UserEntity;
import com.example.demo.springandreact.domain.Product;
import com.example.demo.springandreact.domain.Stock;
import com.example.demo.springandreact.domain.TimeSeries;
import com.example.demo.springandreact.domainbean.TimeSeriesBean;
import com.example.demo.springandreact.provider.StockReader;
import com.example.demo.springandreact.repository.UserRepository;
import com.example.demo.springandreact.service.TimeSeriesService;
import com.example.demo.springandreact.repository.ProductRepository;
import com.example.demo.springandreact.repository.StockRepository;
import com.example.demo.springandreact.repository.TimeSeriesRepository;
import com.opencsv.exceptions.CsvValidationException;

@RestController
@RequestMapping(path="/stocktrack", produces=MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:3000"})
public class TimeSeriesController {
	

	private  StockRepository stockRepository;
	private  TimeSeriesRepository timeSeriesRepository;
	private TimeSeriesService timeSeriesService;
	
	public TimeSeriesController( TimeSeriesRepository timeSeriesRepository, StockRepository stockRepository) {
		 this.timeSeriesRepository = timeSeriesRepository;
		 this.stockRepository = stockRepository;
		 this.timeSeriesService = new TimeSeriesService(timeSeriesRepository, stockRepository);
		 
	}
    
//	@GetMapping("/users")
//    public List<UserEntity> getUsers() {
//        return userRepository.findAll();
//    }
//	 
//	@GetMapping("/products")
//    public List<Product> getProducts() {
//        return productRepository.findAll();
//    }
	
//	@GetMapping("/stocks")
//    public List<Stock> getStocks() {
//        return stockRepository.findAll();
//    }
	
//	@GetMapping("/timeseries")
//    public List<TimeSeries> getTimeseries() {
//        return timeSeriesRepository.findAll();
//    }
	
	
	@GetMapping("/timeseries")
    public ArrayList<TimeSeriesBean> loadTimeseries(@RequestBody String[] tickers) {
		 ArrayList<TimeSeriesBean> timeSeriesList = new ArrayList<>();
		List<TimeSeries> allTimeSeries = timeSeriesService.load(tickers);
		for (TimeSeries timeSeries : allTimeSeries) {
		  TimeSeriesBean timeSeriesBean = new TimeSeriesBean();
       	  timeSeriesBean.setId(timeSeries.getId());
       	  timeSeriesBean.setTicker(timeSeries.getStock().getTicker());
       	  timeSeriesBean.setDate(timeSeries.getTime());
       	  timeSeriesBean.setOpen(String.valueOf(timeSeries.getOpenPrice()));
       	  timeSeriesBean.setClose(String.valueOf(timeSeries.getClosePrice()));
       	  timeSeriesBean.setHigh(String.valueOf(timeSeries.getHighPrice()));
       	  timeSeriesBean.setLow(String.valueOf(timeSeries.getLowPrice()));
       	  timeSeriesList.add(timeSeriesBean);
       	 
		}
		return timeSeriesList;
    }
    
//    @GetMapping("/sayhello")
//    public String sayHello(@RequestParam(value = "name", defaultValue = "world") String name) {
//        return "Hello "+name;
//    }

    @GetMapping("/series") 
    public ArrayList<TimeSeriesBean> fetchTimeSeries() throws CsvValidationException, IOException 
    {
         //Get data from service layer into entityList.

    	 ArrayList<TimeSeriesBean> timeSeriesList = StockReader.readCSV();
    	
        
        return timeSeriesList;
    }
    
//    @GetMapping("/{id}/{name}/{data}")
//    public ResponseEntity<TimeSeriesBean> getData(@PathVariable("id") String id,
//                             @PathVariable("name") String name,
//                                @PathVariable("data") String data) {
//    	
//    	 TimeSeriesBean timeSeriesBean = new TimeSeriesBean();
//   	  timeSeriesBean.setId(id);
//   	  timeSeriesBean.setTicker(name);
//   	  timeSeriesBean.setTime(data);
//   	     
//        HttpHeaders headers = new HttpHeaders();
//         
//        ResponseEntity<TimeSeriesBean> entity = new ResponseEntity<>(TimeSeriesBean,headers,HttpStatus.CREATED);
//         
//        return entity;
//    }


}