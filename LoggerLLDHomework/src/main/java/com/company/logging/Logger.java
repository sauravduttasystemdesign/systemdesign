package com.company.logging;

public class Logger {

    private static final Logger INSTANCE = new Logger();

    private Logger() {
        // Private constructor
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public synchronized void log(String message) {
        System.out.println(message);
    }
}
