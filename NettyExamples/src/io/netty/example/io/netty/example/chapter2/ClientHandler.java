package io.netty.example.io.netty.example.chapter2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // Send a message to the server once the connection is established
        System.out.println("Channel is active");
        ctx.writeAndFlush("Hello, Server!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Print the server's response and close the connection
        System.out.println("Server response: " + msg);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Handle any exceptions and close the context
        cause.printStackTrace();
        ctx.close();
    }
}