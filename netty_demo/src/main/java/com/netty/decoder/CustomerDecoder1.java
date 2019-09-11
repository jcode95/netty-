package com.netty.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;


/***
 * 消息转为消息（解码）
 */
public class CustomerDecoder1 extends MessageToMessageDecoder{
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List list) throws Exception {

    }
}
