package com.example.demo.springandreact.provider;

import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.example.demo.springandreact.domain.Stock;
import com.example.demo.springandreact.domain.TimeSeries;
import com.example.demo.springandreact.domainbean.TimeSeriesBean;
import com.example.demo.springandreact.utils.Constants;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.CSVReader;

/**
 * This represents a component that can read the input sticker codes
 * and query their information from the external API.
 * 
 * @author Kevin Tong
 *
 */
public class StockReader {
	
	
	
	public static ArrayList<Stock> stocks = new ArrayList<Stock>();
	public static File tickerFile = new File("Tickers.txt");
	
	/**
	 * This is the entry point to process the input stickerts and extract the information.
	 * @throws Exception   When the external API is down, or no input file was provided.
	 */
	public static void start() throws Exception {
		obtainTimeSeries();
		writeCSV();
	}
	
	public static void obtainTimeSeries() throws Exception {
		
		Scanner in = new Scanner(tickerFile);
		
		while(in.hasNextLine()){
    		
			Client c = new Client();
        	String ticker = in.nextLine().toUpperCase();
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
            ArrayList<TimeSeries> tsData = new ArrayList<TimeSeries>();
            for(int i = 0; i < values.size(); i++) {
            	JSONObject currentvalue = (JSONObject) values.get(i);
            	TimeSeries tsValue = new TimeSeries((String)currentvalue.get("datetime"), Double.parseDouble((String)currentvalue.get("open")), 
            			Double.parseDouble((String)currentvalue.get("close")), Double.parseDouble((String)currentvalue.get("high")), 
            			Double.parseDouble((String)currentvalue.get("low")));
            	tsData.add(tsValue);
            }
            
           
            
            Stock stock = new Stock((String)meta.get("symbol"));
            stock.setTimeSeries(tsData);
            stocks.add(stock);
            
           
            connection.disconnect();
    	}
		for(int i = 0; i < stocks.size(); i++) {
    		System.out.println(stocks.get(i));
    	}
	}
	public static void writeCSV() {
		File file = new File("output.csv");
		try {
			FileWriter outputfile = new FileWriter(file);
			CSVWriter writer = new CSVWriter(outputfile);
			String[] header = { "Ticker", "Date/Time", "Open", "Close", "High", "Low" };
			writer.writeNext(header);
			for(int i = 0; i < stocks.size(); i++) {
	    		Stock current = stocks.get(i);
	    		for(int j = 0; j < current.getTimeSeries().size(); j++) {
	    			TimeSeries currentData = current.getTimeSeries().get(j);
	    			String[] data = {current.getTicker(), currentData.getTime() + "", currentData.getOpenPrice() + "", currentData.getClosePrice() + "",
	    					currentData.getHighPrice() + "", currentData.getLowPrice() + ""};
	    			writer.writeNext(data);
	    		}
	    	}
			writer.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public static ArrayList<TimeSeriesBean> readCSV() throws IOException, CsvValidationException{
			 ArrayList<TimeSeriesBean> timeSerisList = new ArrayList<>();
		 
		 try (
		            Reader reader = Files.newBufferedReader(Paths.get("output.csv"));
		            CSVReader csvReader = new CSVReader(reader);
		        ) {
			       
		            // Reading Records One by One in a String array
		            String[] nextRecord = csvReader.readNext();
					
		            int id = 1;
		            while ((nextRecord = csvReader.readNext()) != null) {
		            	  TimeSeriesBean timeSeriesBean = new TimeSeriesBean();
		            	  timeSeriesBean.setId(id);
		            	  timeSeriesBean.setTicker(nextRecord[0]);
		            	  timeSeriesBean.setDate(nextRecord[1]);
		            	  timeSeriesBean.setOpen(nextRecord[2]);
		            	  timeSeriesBean.setClose(nextRecord[3]);
		            	  timeSeriesBean.setHigh(nextRecord[4]);
		            	  timeSeriesBean.setLow(nextRecord[5]);
		            	  timeSerisList.add(timeSeriesBean);
		            	  id++;
		            	}
		                
		            }
		        
		return timeSerisList;
	}
	

	
//	public static HashMap<String, ArrayList<TimeSeries>> readCSV() throws IOException, CsvValidationException{
//		 HashMap<String, ArrayList<TimeSeries>> stockMap = new HashMap<String, ArrayList<TimeSeries>>();
//		 
//		 try (
//		            Reader reader = Files.newBufferedReader(Paths.get("output.csv"));
//		            CSVReader csvReader = new CSVReader(reader);
//		        ) {
//			       
//		            // Reading Records One by One in a String array
//		            String[] nextRecord;
//		            while ((nextRecord = csvReader.readNext()) != null) {
//		            	if (stockMap.containsKey(nextRecord[0]))
//		            	{
//		            		ArrayList<TimeSeries> listOfTime = stockMap.get((nextRecord[0]));
//		            		listOfTime.add(new TimeSeries (nextRecord[1], Double.parseDouble(nextRecord[2]), Double.parseDouble(nextRecord[3]), 
//		            					Double.parseDouble(nextRecord[4]), Double.parseDouble(nextRecord[5])));
//		            	} else {
//		            		ArrayList<TimeSeries> listOfTime = new ArrayList<TimeSeries>();
//		            		listOfTime.add(new TimeSeries (nextRecord[1], Double.parseDouble(nextRecord[2]), Double.parseDouble(nextRecord[3]), 
//	            					Double.parseDouble(nextRecord[4]), Double.parseDouble(nextRecord[5])));
//		            		stockMap.put(nextRecord[0], listOfTime);
//		            	}
//		            	
//		            
//		                
//		            }
//		        }
//		return stockMap;
//	}
}



