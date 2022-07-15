package com.example.demo.springandreact;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.springandreact.domain.Stock;
import com.example.demo.springandreact.domain.TimeSeries;
import com.example.demo.springandreact.repository.StockRepository;
import com.example.demo.springandreact.repository.TimeSeriesRepository;

//import com.example.demo.springandreact.repository.ProductRepository;
//import com.example.demo.springandreact.domain.Product;
//import com.example.demo.springandreact.provider.StockReader;

@SpringBootApplication
public class SpringAndReactApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAndReactApplication.class, args);
    }
    
    
//    @Bean
//    CommandLineRunner runner(StockRepository stockRepository, 
//    						 TimeSeriesRepository timeSeriesRepository) {
//    						
//        return args -> {
//        	
//        	 // add a stock
//            Stock stock = new Stock("GOOG");
//           
//            // save the book
//            stockRepository.save(stock);
//            
//            //get data from database => select * from Storck where tiker = 'GOOG'
//            stock = stockRepository.findByTicker("GOOG");
//            
//            
//            
//            // create and save new time series      
//            timeSeriesRepository.save(new TimeSeries("2022/7/13 21:33:23", 34.56, 34.01, 35.67, 32.11, stock));
//            timeSeriesRepository.save(new TimeSeries("2022/7/13 22:36:56", 35.01, 36.01, 37.87, 34.15, stock));
//            timeSeriesRepository.save(new TimeSeries("2022/7/13 21:53:21", 36.00, 36.55, 36.88, 35.31, stock));
//        };
//    }
}


// -----------------------------------------------------------------------    		
//		@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
//
//		};
//	}
		
//		@Bean
//	    CommandLineRunner runner(ProductRepository productRepository) {
//	        return args -> {
//	            
//	            Product product1 = new Product("Xbox 360");
//	            product1.setPrice(299.00);
//	            productRepository.save(product1);
//	            
//	            Product product2 = new Product("Wii");
//	            product2.setPrice(269.00);
//	            productRepository.save(product2);
//	            
//	            Product product3 = new Product("Wireless Controller");
//	            product3.setPrice(19.99);
//	            productRepository.save(product3);
//	            
//	        };
//	    }
		
//		@Bean
//	    CommandLineRunner runner() {
//	        return args -> {
//	            
//	        	StockReader.start();
//	        };
//	    }



