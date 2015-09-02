package com.pelf.requeststructure;

import java.util.List;

import com.pelf.util.CompressionHeader;
import com.pelf.util.MessageHeader;
import com.pelf.util.Request;
import com.pelf.util.TransactionCode;
import com.pelf.util.Utils;





public class MarketDepthRequest extends Request {
    //private int token;

    public MarketDepthRequest(List<Integer> tokens)
    {
        //this.token = token;
        this.compHeader = new CompressionHeader((short)410,(short) 410);
        this.msgHeader =  new  MessageHeader((short)410,TransactionCode.Broadcast_Request_Response);
        this.struct = new byte[414];
        System.arraycopy(this.compHeader.getByteArray(), 0, this.struct, 0, 4);
        System.arraycopy(this.msgHeader.getByteArray(), 0, this.struct, 4, 4);
        Utils.setShort((short)1, struct, 8);
        int kindex = 10;
        for(int token : tokens){
        	Utils.setInt(token, struct, kindex);
        	kindex = kindex + 4;
        }
        Utils.setShort((short)1, struct, struct.length-3);
    }
    
    
}
