package com.netty.io.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class OutboundHandler1 extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf data= (ByteBuf) msg;

//        System.out.println("Outbound1 "+data.toString(CharsetUtil.UTF_8));
//        ctx.write(Unpooled.copiedBuffer("<--outbound1"+data.toString(CharsetUtil.UTF_8),CharsetUtil.UTF_8));
//        ctx.flush();
        ctx.writeAndFlush(Unpooled.copiedBuffer("<--outbound1"+data.toString(CharsetUtil.UTF_8),CharsetUtil.UTF_8));


    }
}
