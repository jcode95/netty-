package com.netty.semi.packed;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import jdk.nashorn.internal.runtime.linker.Bootstrap;

/***
 * 半包读写问题实例
 */
public class EchoServer {

    private int port;
    NioEventLoopGroup boss=null;
    NioEventLoopGroup work=null;

    public EchoServer(int port) {
        this.port = port;
    }


    public void run() {

        try {
             boss = new NioEventLoopGroup();
             work = new NioEventLoopGroup();
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, work).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            //处理半包读写begin
//                            pipeline.addLast(new LineBasedFrameDecoder(1024));

                            pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("@_".getBytes())));//自定义的解码器，以"@_"来分隔符
                            pipeline.addLast(new StringDecoder());
                            //处理半包读写end
                            pipeline.addLast(new EchoServerhandler());
                        }
                    });


            System.out.println("echo 启动成功，端口号：" + port);
            //绑定端口，同步阻塞成功
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            ///关闭资源
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new EchoServer(port).run();


    }
}
