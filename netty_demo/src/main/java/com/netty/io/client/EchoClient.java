package com.netty.io.client;

import com.netty.io.server.EchoServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

public class EchoClient {
    private NioEventLoopGroup nioEventLoopGroup=null;
    private Bootstrap bootstrap=null;
    private  String host="127.0.0.1";
    private  int port=8080;



    public void start(){
        try {
            nioEventLoopGroup=new NioEventLoopGroup();
            bootstrap=new Bootstrap();
            bootstrap.group(nioEventLoopGroup).channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
//                            pipeline.addLast(new EchoClienthandler());
                            pipeline.addLast(new MessageHandler());
                        }
                    });

            bootstrap.option(ChannelOption.SO_BACKLOG,1024)//连接的请求队列
                    .option(ChannelOption.SO_KEEPALIVE,true)//SO_KEEPALIVE=true,是利用TCP的SO_KEEPALIVE属性,当SO_KEEPALIVE=true的时候,服务端可以探测客户端的连接是否还存活着,如果客户端因为断电或者网络问题或者客户端挂掉了等,那么服务端的连接可以关闭掉,释放资源。
                    .option(ChannelOption.SO_REUSEADDR,true)//复用地址设置SO_REUSEADDR为true,意味着地址可以复用
                    .option(ChannelOption.TCP_NODELAY,true);//如果TCP_NODELAY没有设置为true,那么底层的TCP为了能减少交互次数,会将网络数据积累到一定的数量后,服务器端才发送出去,会造成一定的延迟。在互联网应用中,通常希望服务是低延迟的,建议将TCP_NODELAY设置为true。

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
        int port = 8899;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        new EchoClient().start();


    }


}
