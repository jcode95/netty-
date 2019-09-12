package com.netty.semi.packed;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
/***
 * 半包读写问题实例
 */
public class EchoClient {
    private NioEventLoopGroup nioEventLoopGroup=null;
    private Bootstrap bootstrap=null;
    private  String host="127.0.0.1";
    private  int por=8080;



    public void start(){
        try {
            nioEventLoopGroup=new NioEventLoopGroup();
            bootstrap=new Bootstrap();
            bootstrap.group(nioEventLoopGroup).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,por))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            pipeline.addLast(new EchoClienthandler());
                        }
                    });
            //连接到服务端
            ChannelFuture channelFuture = bootstrap.connect().sync();
            //
            channelFuture.channel().closeFuture().sync();

//            channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer("我是来自client  @@@@", CharsetUtil.UTF_8));
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(nioEventLoopGroup!=null){
                nioEventLoopGroup.shutdownGracefully();
            }

        }

    }



    public static void main(String[] args) {
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new EchoClient().start();


    }


}
