package com.pelf.client;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.zip.Inflater;

import com.pelf.requeststructure.MarketDepthRequest;
import com.pelf.responsestructure.MarketWatchResponse;
import com.pelf.util.CompressionHeader;
import com.pelf.util.LoggerUtil;
import com.pelf.util.MessageHeader;
import com.pelf.util.Request;
import com.pelf.util.TransactionCode;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class InBoundDataParser extends SimpleChannelInboundHandler<Object> {
	
	private DataDispatcher dataDispatcher;
	private List<Integer> tokens;
	
	InBoundDataParser(DataDispatcher dataDispatcher, List<Integer> tokens){
		this.dataDispatcher = dataDispatcher;
		this.tokens = tokens;
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Request req = new MarketDepthRequest(tokens);
		ctx.writeAndFlush(req).sync();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		parseData((byte[]) msg);
	}

	
	private void parseData(byte[] data){
    	CompressionHeader compHeader = new CompressionHeader(data);
        //System.out.println("New Packet Recieved, Compressed Size:"+compHeader.MsgCompLen+"\tDecopressed Size:"+compHeader.MsgLen);
        
        byte[] message = new byte[data.length-4];
        System.arraycopy(data, 4, message, 0, message.length);
         //<editor-fold desc="Decompression Logic">
        if(compHeader.MsgCompLen!=compHeader.MsgLen){//Decompression required
      	  try {
      		  Inflater inflater = new Inflater();
      		  inflater.setInput(message);
      		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compHeader.MsgLen);
      		  byte[] buffer = new byte[1024];  
      		  while (!inflater.finished()) {  
      			  int count = 0;  
      			  count = inflater.inflate(buffer);
      			  outputStream.write(buffer, 0, count);  
      		  }  
                outputStream.close();
           
                byte[] output = outputStream.toByteArray();  
                inflater.end();
                message = output;
      	  } catch (Exception ex) {
                LoggerUtil.getLogger().log(Level.SEVERE, "Decompressing in parsetick data failed", ex);
            }
        }
        //</editor-fold>
        
        //Read the uncompressed (if was compressed) payload here
         byte[] headerBytes = new byte[4];
         System.arraycopy(message, 0, headerBytes, 0, 4);
         MessageHeader header = new MessageHeader(headerBytes);
         //System.out.println("Message Length:"+header.MsgLen);
         //System.out.println("Message Code:"+header.MsgCode); 
         //System.out.println("Message Parsed, Message was compressed:"+(compHeader.MsgCompLen!=compHeader.MsgLen));
        if(header.MsgCode==TransactionCode.Broadcast_Request_Response){
           MarketWatchResponse watch = new MarketWatchResponse(message);
           dataDispatcher.saveTickData(watch);
           //System.out.println(sym+" "+watch.getBestBid()+" "+watch.getDayHigh()+" "+watch.getDayLow());
        }
    }
}
