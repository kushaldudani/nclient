package com.pelf.client;

import java.util.List;

import com.pelf.util.Utils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ByteToObjectConverter extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if (in.readableBytes() <= 2) {
            return;
        }
		byte[] array = new byte[2];
		in.getBytes(in.readerIndex(), array);
		short msgLength = Utils.getInt16(array, 0);
		if(in.readableBytes() < msgLength+4){
			return;
		}
		int datasize = msgLength+4;
		byte[] data = new byte[datasize];
		in.readBytes(data);
		out.add(data);
	}

}
