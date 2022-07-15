package com.example.demo.springandreact.domainbean;


//Importing class
import lombok.Data;

//Annotation
@Data
//Class
public class TimeSeriesBean {

 long id;
 String date;
 String ticker;
 String open;
 String close;
 String high;
 String low;
}