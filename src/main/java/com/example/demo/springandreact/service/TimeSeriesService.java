package com.example.demo.springandreact.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.springandreact.domain.Product;
import com.example.demo.springandreact.domain.Stock;
import com.example.demo.springandreact.domain.TimeSeries;
import com.example.demo.springandreact.provider.Client;
import com.example.demo.springandreact.repository.ProductRepository;
import com.example.demo.springandreact.repository.StockRepository;
import com.example.demo.springandreact.repository.TimeSeriesRepository;
import com.example.demo.springandreact.utils.Constants;

public class TimeSeriesService implements ITimeSeriesService{
		private TimeSeriesRepository timeSeriesRepository;
		private StockRepository stockReposity;
    
    @Autowired
    public TimeSeriesService(TimeSeriesRepository timeSeriesRepository, StockRepository stockReposity){
        this.timeSeriesRepository = timeSeriesRepository;
        this.stockReposity = stockReposity;
    }

    @Override
    public List<TimeSeries> list() {
        return timeSeriesRepository.findAll();
    }

    @Override
    public Optional<TimeSeries> read(long id) {
        return timeSeriesRepository.findById(id);
    }

    @Override
    @Transactional
    public TimeSeries create(TimeSeries timeSeries) {
        // save the new timeSeries
        return timeSeriesRepository.save(timeSeries);
    }

    @Override
    public void delete(long id) {
    	timeSeriesRepository.deleteById(id);
    }

    
    @Override
    @Transactional
    public List <TimeSeries> load (String [] tickers) {
        // save the new timeSeries
    	loadTimeSeries(tickers);
    	
        return timeSeriesRepository.findAll();
    }
   
    
    private  void loadTimeSeries(String [] tickers)  {
    	 ArrayList<TimeSeries> tsData = new ArrayList<TimeSeries>();
		
    	 try {
		 for(String ticker : tickers){
			
			
			//save to  stock table
			Stock stock = new Stock(ticker);
			//the stock has primary key after save
			stock = stockReposity.save(stock);
			
			Client c = new Client();
        	String TimeSeriesURL = "https://api.twelvedata.com/time_series?symbol=" + ticker + "&interval=" + c.getInterval()
        			+ "&outputsize=" + c.getOutputsize() + "&apikey=" + Constants.API_KEY;
            URL tsRequest = new URL(TimeSeriesURL);
            HttpURLConnection connection = (HttpURLConnection)tsRequest.openConnection();
            StringBuffer responseData = new StringBuffer();
            JSONParser parser = new JSONParser();
     
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "twelve_java/1.0");
            connection.connect();
     
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Request failed. Error: " + connection.getResponseMessage());
            }
     
            Scanner scanner = new Scanner(tsRequest.openStream());
            while (scanner.hasNextLine()) {
                responseData.append(scanner.nextLine());
            }
            
            System.out.println(responseData);
            
     
            JSONObject json = (JSONObject) parser.parse(responseData.toString());
            JSONObject meta = (JSONObject) json.get("meta");
            JSONArray values = (JSONArray) json.get("values");
           
            for(int i = 0; i < values.size(); i++) {
            	JSONObject currentvalue = (JSONObject) values.get(i);
            	TimeSeries tsValue = new TimeSeries((String)currentvalue.get("datetime"), Double.parseDouble((String)currentvalue.get("open")), 
            			Double.parseDouble((String)currentvalue.get("close")), Double.parseDouble((String)currentvalue.get("high")), 
            			Double.parseDouble((String)currentvalue.get("low")), stock);
            	tsData.add(tsValue);
            	
            	timeSeriesRepository.save(tsValue);
            }
            
                     
           
            connection.disconnect();
    	}
    	 }catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	
    }
}
