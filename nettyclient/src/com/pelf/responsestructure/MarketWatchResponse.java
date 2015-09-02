
package com.pelf.responsestructure;


import java.util.Calendar;
import java.util.GregorianCalendar;

import com.pelf.util.Utils;



public class MarketWatchResponse {
    public int Token;
    public int Segment;
    public int Last_Rate;
    public int Last_Quantity;
    public int Total_Quantity;
    public int High;
    public int Low;
    public int Open_Rate;
    public int Previous_Close;
    public int Average_Rate;
    public int Best_Bid_Qty;
    public int Best_Bid_Rate;
    public int Best_Ask_Quantity;
    public int Best_Ask_Rate;
    public int Total_Bid_Qty;
    public int Total_Ask_Qty;
    public int Time;
    public int Open_Interest;
    public int Change_In_OI;
    public int Day_High_OI;
    public int Day_Low_OI;
    public int Upper_Circuit;
    public int Lower_Circuit;
    public int Highest_Ever;
    public int Lowest_Ever;
    public MarketWatchResponse(){
        
    }
    public MarketWatchResponse(byte[] data){
        Token = Utils.getInt32(data, 4);
        Segment = Utils.getInt32(data, 8);
        Last_Rate = Utils.getInt32(data, 12);
        
        Last_Quantity = Utils.getInt32(data, 16);
        Total_Quantity = Utils.getInt32(data, 20);
        High = Utils.getInt32(data, 24);
        Low = Utils.getInt32(data, 28);
        Open_Rate = Utils.getInt32(data, 32);
        Previous_Close = Utils.getInt32(data, 36);
        Average_Rate = Utils.getInt32(data, 40);
        Best_Bid_Qty = Utils.getInt32(data, 44);
        Best_Bid_Rate = Utils.getInt32(data, 48);
        Best_Ask_Quantity = Utils.getInt32(data, 52);
        Best_Ask_Rate = Utils.getInt32(data, 56);
        Total_Bid_Qty = Utils.getInt32(data, 60);
        Total_Ask_Qty = Utils.getInt32(data, 64);
        Time = Utils.getInt32(data, 68);
        Open_Interest = Utils.getInt32(data, 72);
        Change_In_OI = Utils.getInt32(data, 76);
        Day_High_OI = Utils.getInt32(data, 80);
        Day_Low_OI = Utils.getInt32(data, 84);
        Upper_Circuit = Utils.getInt32(data, 88);
        Lower_Circuit = Utils.getInt32(data, 92);
        Highest_Ever = Utils.getInt32(data, 96);
        Lowest_Ever = Utils.getInt32(data, 100);
        //System.out.println(Token+"\t"+Best_Bid_Rate+"\t"+Best_Bid_Qty+"\t"+Upper_Circuit+"\t"+Time);
        
    }
    
    public float getBestBid(){
        return (float)(this.Best_Bid_Rate/(float)100.00);
    }
    public float getBestAsk(){
        return (float)(this.Best_Ask_Rate/(float)100.00);
    }
    public float getDayHigh(){
        return (float)(this.High/(float)100.00);
    }
    public float getDayLow(){
        return (float)(this.Low/(float)100.00);
    }
    
    public float getLTP(){
        return (float)(this.Last_Rate/(float)100.00);
    }
    public float getOpen(){
        return (float)(this.Open_Rate/(float)100.00);
    }
    
    public long getVolume(){
    return this.Total_Quantity;
    }
      
    public long getLTDate(){
        
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.MONTH,0);
        cal.set(Calendar.YEAR,1980);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.setTimeInMillis(cal.getTimeInMillis()+((long)Time*1000L));
        return cal.getTimeInMillis();
    }
    
    
    public long getMillis(){
        
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.MONTH,0);
        cal.set(Calendar.YEAR,1980);
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.roll(Calendar.SECOND, Time);
        return cal.getTimeInMillis();
    }
	public int getToken() {
		return Token;
	}

    

    
}
