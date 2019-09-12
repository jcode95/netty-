package com.netty.semi.packed;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
