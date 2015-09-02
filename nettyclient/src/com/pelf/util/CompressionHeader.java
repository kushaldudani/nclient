
package com.pelf.util;


public class CompressionHeader {
    public short    MsgCompLen;
    public short    MsgLen;

    public CompressionHeader(short MsgCompLen, short MsgLen) {
        this.MsgCompLen = MsgCompLen;
        this.MsgLen = MsgLen;
    }
     public CompressionHeader(byte[] bytes){
        MsgCompLen = Utils.getInt16(bytes, 0);
        MsgLen = Utils.getInt16(bytes, 2);
    }
    
      public byte[] getByteArray(){
        byte[] headerBytes = new byte[4];
        Utils.setShort(MsgCompLen, headerBytes, 0);
        Utils.setShort(MsgLen, headerBytes, 2);
        return headerBytes;
    }
}
