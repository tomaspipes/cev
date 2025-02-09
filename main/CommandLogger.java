package main;

import java.io.*;

public class CommandLogger {

    private static final String LOG_FILE_PATH = "command.log";
    private static BufferedWriter writer;

    static {
        try {
            writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH, true));
        } catch (IOException e) {
            System.out.println("Error opening the log file.");
        }
    }

    public static void log(String message) {
        if (writer != null) {
            try {
                writer.write(message);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                System.out.println("Error writing to log file.");
            }
        }
    }

    public static void logException(Exception e) {
        if (writer != null) {
            try {
                writer.write("Exception: " + e.getMessage());
                writer.newLine();
                writer.flush();
            } catch (IOException ex) {
                System.out.println("Error writing exception to log file.");
            }
        }
    }

    public static void clearLog() {
        try {
            writer = new BufferedWriter(new FileWriter(LOG_FILE_PATH));
        } catch (IOException e) {
            System.out.println("Error clearing the log file.");
        }
    }

    public static void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error closing the log file.");
        }
    }
}

