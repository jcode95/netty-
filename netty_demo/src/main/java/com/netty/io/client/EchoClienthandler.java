package com.netty.io.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.spdy.SpdySynReplyFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;


import java.nio.ByteBuffer;

public class EchoClienthandler extends SimpleChannelInboundHandler<ByteBuf> {




    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive@@@@@@@11111111111");
        //写入数据到服务端
//        Request request = new Request();

        ctx.writeAndFlush(Unpooled.copiedBuffer("我是client d channelActive",CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete@33333333333333333333333333");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        cause.printStackTrace();
    }

    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        /*第一种*/
//        Channel channel = ctx.channel();
//        channel.writeAndFlush()
        /*第二种*/
//        ChannelPipeline pipeline = ctx.pipeline();
//        pipeline.writeAndFlush()
        /*第三种*/
//        ctx.writeAndFlush()


        System.out.println("client -==22222222222="+msg.toString(CharsetUtil.UTF_8));

    }
}
