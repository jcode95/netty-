package com.netty.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/***
 * ByteToMessageDecoder吧字节转为消息
 */
public class CustomerDecoder extends ByteToMessageDecoder{
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        //int 是4个字节，需要检查是否满足
        if(in.readableBytes()>=4){
            //添加到解码信息里面
            out.add(in.readInt());
        }

    }
}
