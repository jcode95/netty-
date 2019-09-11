package com.netty.io.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

public class a {

    public static void main(String[] args) {
        //tiny规格内存分配 会变成大于等于16的整数倍的数：这里254 会规格化为256
        ByteBufAllocator alloc = PooledByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = alloc.directBuffer(254);    //读写bytebuf
         byteBuf.writeInt(126);
        System.out.println(byteBuf.readInt());    //很重要，内存释放
         byteBuf.release();
    }
}
