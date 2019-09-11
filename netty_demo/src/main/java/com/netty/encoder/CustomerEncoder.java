package com.netty.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class CustomerEncoder extends MessageToByteEncoder{
    protected void encode(ChannelHandlerContext ctx, Object o, ByteBuf data) throws Exception {
        ctx.write(data);
    }
}
