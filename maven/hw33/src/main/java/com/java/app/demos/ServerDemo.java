package com.java.app.demos;

import com.java.app.netty.Server;

public class ServerDemo {
    private static final int PORT = 8080;


    static void main(String[] args) {
        try {
            new Server(PORT).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
