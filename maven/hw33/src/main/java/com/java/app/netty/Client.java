package com.java.app.netty;

import com.java.app.netty.handlers.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run(final String message) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ChannelPipeline pipeline = ch.pipeline();
                            // Додайте обробники (handlers) до пайплайну
                            pipeline.addLast(new ClientHandler());
                        }
                    });

            Channel channel = bootstrap.connect(host, port).sync().channel();

            // Відправка повідомлення на сервер
            channel.writeAndFlush(Unpooled.copiedBuffer(message.getBytes()));

            // Очікуємо завершення з'єднання та закриваємо канал
            channel.closeFuture().sync();
            channel.close();
        } finally {
            group.shutdownGracefully();
        }
    }
}
