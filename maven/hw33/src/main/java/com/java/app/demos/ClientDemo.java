package com.java.app.demos;

import com.java.app.netty.Client;

public class ClientDemo {
    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    static void main(String[] args) {
        try {
            Client client = new Client(HOST, PORT);
            client.run("Hello Netty Server!");
            //? For testing exit command uncomment a line of code below
//            client.run("exit");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
