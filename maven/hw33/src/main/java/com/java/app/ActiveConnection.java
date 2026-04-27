package com.java.app;

import io.netty.channel.Channel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ActiveConnection {
    private final String name;
    private LocalDateTime connectedAt;
    private final Channel channel;
}
