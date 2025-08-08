package org.soham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class JavaUnstructuredLogs {

    private static final Logger logger = LoggerFactory.getLogger(JavaUnstructuredLogs.class);
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Sample unstructured log entries (output based on logback.xml PatternLayout)
        logger.info("This is a simple informational message from the application.");
        logger.warn("Attention: A potential issue has been detected, please investigate.");
        logger.error("CRITICAL ERROR: Application encountered an unrecoverable state.");

        System.out.println("\n--- Generating a larger unstructured log file (unstructured_logs.log) ---");
        String[] generalMessages = {
                "Initiating background task.",
                "Task completed without errors.",
                "Checking system resources.",
                "Connection established to remote host.",
                "Data synchronization in progress.",
                "Unexpected event occurred.",
                "Configuration loaded successfully.",
                "User activity detected on portal.",
                "Backup operation started.",
                "Failed to acquire lock for resource.",
                "Service health check failed."
        };
        String[] levels = {"INFO", "DEBUG", "WARN", "ERROR"};

        for (int i = 0; i < 5000; i++) { // Adjust for 10MB
            String message = generalMessages[random.nextInt(generalMessages.length)];
            String level = levels[random.nextInt(levels.length)];
            // Just log the message directly to simulate unstructured text
            switch (level) {
                case "INFO":
                    logger.info("Entry {}: {}", i + 1, message);
                    break;
                case "DEBUG":
                    logger.debug("Entry {}: {}", i + 1, message);
                    break;
                case "WARN":
                    logger.warn("Entry {}: {}", i + 1, message);
                    break;
                case "ERROR":
                    logger.error("Entry {}: {}", i + 1, message);
                    break;
            }
        }
        System.out.println("Finished generating unstructured_logs.log");
    }
}
