package com.java.app.hw38;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw38Application implements CommandLineRunner {

    private final TUI tui;

    public Hw38Application(TUI tui) {
        this.tui = tui;
    }

    public static void main(String[] args) {
        SpringApplication.run(Hw38Application.class, args);
    }

    @Override
    public void run(String... args) {
        tui.run();
    }


}
