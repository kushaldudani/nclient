package com.pelf.client;

import com.pelf.util.Request;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ObjectToByteConverter extends MessageToByteEncoder<Request> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Request msg, ByteBuf out)
			throws Exception {
		byte[] array = msg.getStruct();
		out.writeBytes(array);
	}

}
