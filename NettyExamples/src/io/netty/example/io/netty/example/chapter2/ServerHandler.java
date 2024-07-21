package io.netty.example.io.netty.example.chapter2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Print the received message
        System.out.println("Received: " + msg);
        // Send a response back to the client
        ctx.writeAndFlush("Hello, Client!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Handle any exceptions and close the context
        cause.printStackTrace();
        ctx.close();
    }
}