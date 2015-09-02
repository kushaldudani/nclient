package com.pelf.client;

import java.util.List;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class BroadCastClient {
	
	static final String HOST = "203.123.141.236";
    static final int PORT = 46026;
    // Reconnect when the server sends nothing for 10 seconds.
    static final int READ_TIMEOUT = 10;
    static final int RECONNECT_DELAY = 1;
	
    private final UptimeClientHandler handler = new UptimeClientHandler();
    private static DataDispatcher dataDispatcher;
    private static List<Integer> tokens;
    
    public void mainrun(DataDispatcher datadispatcher, List<Integer> tkns) throws Exception {
    	dataDispatcher = datadispatcher;
    	tokens = tkns;
    	EventLoopGroup workerGroup = new NioEventLoopGroup();
    	try {
    		configureBootstrap(new Bootstrap(), workerGroup).connect();
    	}finally {
            workerGroup.shutdownGracefully();
        }
    }
    
    Bootstrap configureBootstrap(Bootstrap b, EventLoopGroup g) {
        b.group(g)
         .channel(NioSocketChannel.class)
         .remoteAddress(HOST, PORT)
         .option(ChannelOption.SO_KEEPALIVE, true)
         .handler(new ChannelInitializer<SocketChannel>() {
             @Override
             public void initChannel(SocketChannel ch) throws Exception {
                 ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT, 0, 0), handler);
                 ch.pipeline().addLast(new ByteToObjectConverter());
                 ch.pipeline().addLast(new ObjectToByteConverter());
                 ch.pipeline().addLast(new InBoundDataParser(dataDispatcher, tokens));
             }
         });

        return b;
    }

    void connect(Bootstrap b) {
        b.connect().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.cause() != null) {
                    handler.startTime = -1;
                    handler.println("Failed to connect: " + future.cause());
                }
            }
        });
    }
}
