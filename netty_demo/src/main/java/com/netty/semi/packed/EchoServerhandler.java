package com.netty.semi.packed;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class EchoServerhandler extends ChannelInboundHandlerAdapter{
    private int count;
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

   /* @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            ByteBuf byteBuf=(ByteBuf)msg;
            byte[] bytes=new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            String body=new String(bytes,"UTF-8")
                    .substring(0,bytes.length-System.getProperty("line.separator").length());
            System.out.println("服务端收到数据"+body+",次数为："+ ++count);
    }*/

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       String body= (String) msg;
        System.out.println("服务端收到数据"+body+"次数为："+ ++count);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
