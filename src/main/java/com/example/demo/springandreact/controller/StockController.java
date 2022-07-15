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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.example.demo.springandreact.repository.ProductRepository;
import com.example.demo.springandreact.repository.StockRepository;
import com.example.demo.springandreact.repository.TimeSeriesRepository;
import com.opencsv.exceptions.CsvValidationException;

@RestController
@RequestMapping(path="/stocktrack", produces=MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = {"http://localhost:3000"})
public class StockController {
	

	private final StockRepository stockRepository;
	
	
	public StockController(StockRepository stockRepository) {
		 this.stockRepository = stockRepository;
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
	
	@PostMapping("/stocks")
    public List<Stock> creatStocks(@RequestBody String[] tickers) {
		
		for(String ticker : tickers) {
			Stock stock = new Stock(ticker);
			stockRepository.save(stock);
		}
        return stockRepository.findAll();
    }
	
	@DeleteMapping("/stocks")
    public List<Stock> deleteStocks(@RequestBody String[] tickers) {
		
		for(String ticker : tickers) {
			stockRepository.deleteByTicker(ticker);
		}
        return stockRepository.findAll();
    }
	
	@GetMapping("/allstocks")
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
    
    @GetMapping("/sayhello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "world") String name) {
        return "Hello "+name;
    }

//    @GetMapping("/series") 
//    public ArrayList<TimeSeriesBean> fetchTimeSeries() throws CsvValidationException, IOException 
//    {
//         //Get data from service layer into entityList.
//
//    	 ArrayList<TimeSeriesBean> timeSeriesList = StockReader.readCSV();
//    	
//        
//        return timeSeriesList;
//    }
    
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