package com.java.app.netty.handlers;

import com.java.app.ActiveConnection;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {
    private static final Set<ActiveConnection> activeConnections = ConcurrentHashMap.newKeySet();
    private static final AtomicInteger counter = new AtomicInteger(0);

    private ActiveConnection getConnectionByChannelId(ChannelId id) {
        return activeConnections.stream()
                .filter(conn -> conn.getChannel().id().equals(id))
                .findFirst().orElse(null);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String clientName = "client-" + counter.incrementAndGet();
        ActiveConnection connection = new ActiveConnection(clientName, ctx.channel());
        activeConnections.add(connection);
        log.info("[SERVER] {} успішно підключився", clientName);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        try {
            StringBuilder sb = new StringBuilder();
            while (in.isReadable()) {
                sb.append((char) in.readByte());
            }

            String message = sb.toString().trim();
            ActiveConnection connection = getConnectionByChannelId(ctx.channel().id());
            log.info("[SERVER] Повідомлення від {}: {}", connection.getName(), message);

            if ("exit".equalsIgnoreCase(message)) {
                log.info("[SERVER] {} відключається...", connection.getName());
                activeConnections.removeIf(c -> c.getChannel().equals(ctx.channel()));
                ctx.close();
            }

        } finally {
            in.release();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        ActiveConnection ac = getConnectionByChannelId(ctx.channel().id());
        boolean res = activeConnections.remove(ac);
        if (ctx.channel() != null && res) {
            log.info("[SERVER] {} відключився", ac.getName());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
