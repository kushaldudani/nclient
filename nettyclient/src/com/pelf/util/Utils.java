package com.pelf.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class Utils {

	public static String getString(byte[] bytes,int index, int size) throws Exception{
        byte[] array = new byte[size];
        System.arraycopy(bytes, index, array, 0, array.length);
        return getString(array);
    }
    
    public static String getString(byte[] data) throws Exception{
         return new String(data,Charset.forName("UTF-8")).replaceAll("\0", "");
    }
    
    public static int getInt32(byte[] bytes, int beginIndex){
    ByteBuffer buffer = ByteBuffer.allocate(4);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    System.arraycopy(bytes, beginIndex, buffer.array(), 0, buffer.array().length);
    return buffer.getInt();
    }
    public static double getDouble(byte[] bytes, int beginIndex){
    ByteBuffer buffer = ByteBuffer.allocate(8);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    System.arraycopy(bytes, beginIndex, buffer.array(), 0, buffer.array().length);
    return buffer.getDouble();
    }
    public static long getLong(byte[] bytes, int beginIndex){
    ByteBuffer buffer = ByteBuffer.allocate(8);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    System.arraycopy(bytes, beginIndex, buffer.array(), 0, buffer.array().length);
    return buffer.getLong();
    }
    
    public static short getInt16(byte[] bytes, int beginIndex){
    ByteBuffer buffer = ByteBuffer.allocate(2);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    System.arraycopy(bytes, beginIndex, buffer.array(), 0, buffer.array().length);
    return buffer.getShort();
    }
    
    
    
    public static void setInt(int int32,byte[] bytes,int index){
    ByteBuffer buff = ByteBuffer.allocate(4);
    buff.order(ByteOrder.LITTLE_ENDIAN);
    buff.putInt((int)int32);
    System.arraycopy(buff.array() , 0, bytes, index, buff.array().length);
    }
   
    public static void setDouble(double double64,byte[] bytes,int index){
    ByteBuffer buff = ByteBuffer.allocate(8);
    buff.order(ByteOrder.LITTLE_ENDIAN);
    buff.putDouble(double64);
    System.arraycopy(buff.array() , 0, bytes, index, buff.array().length);
    }
    public static void setLong(long double64,byte[] bytes,int index){
    ByteBuffer buff = ByteBuffer.allocate(8);
    buff.order(ByteOrder.LITTLE_ENDIAN);
    buff.putLong(double64);
    System.arraycopy(buff.array() , 0, bytes, index, buff.array().length);
    }
    public static void setShort(short int16, byte[] bytes, int index){
    ByteBuffer buff = ByteBuffer.allocate(2);
    buff.order(ByteOrder.LITTLE_ENDIAN);
    buff.putShort(int16);
    System.arraycopy(buff.array() , 0, bytes, index, buff.array().length);
    }
    
    public static void setString(String str, int size, byte[] array,int index) throws UnsupportedEncodingException{
        byte[] byteArray = new byte[size];
        byte[] stringBytes=str.getBytes("ISO-8859-1");
        System.arraycopy(stringBytes, 0, byteArray, 0, stringBytes.length);
        System.arraycopy(byteArray, 0, array, index, byteArray.length);
    }
	
}
