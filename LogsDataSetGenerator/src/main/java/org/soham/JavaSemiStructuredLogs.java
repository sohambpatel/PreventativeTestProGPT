package org.soham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class JavaSemiStructuredLogs {

    private static final Logger logger = LoggerFactory.getLogger(JavaSemiStructuredLogs.class);
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Sample semi-structured log entries (output based on logback.xml PatternLayout)
        logger.info("Application startup complete. Version: 1.0.2, Build: 20250728");
        logger.debug("Database connection pool status: Active=10, Idle=5");
        logger.warn("High CPU usage detected on server. Current load: 85%");

        try {
            String data = null;
            data.length(); // This will throw NullPointerException
        } catch (NullPointerException e) {
            logger.error("An unexpected NullPointerException occurred during data processing.", e);
        }

        System.out.println("\n--- Generating a larger semi-structured log file (semi_structured_logs.log) ---");
        String[] services = {"AuthService", "InventoryService", "OrderService", "NotificationService"};
        String[] logMessages = {
                "Processing request for order",
                "User authenticated successfully",
                "Item added to cart",
                "Sending notification email",
                "Database query executed",
                "Cache hit for product ID",
                "External API call failed",
                "Resource not found"
        };
        String[] levels = {"INFO", "DEBUG", "WARN", "ERROR"};

        for (int i = 0; i < 5000; i++) { // Adjust for 10MB
            String service = services[random.nextInt(services.length)];
            String message = logMessages[random.nextInt(logMessages.length)];
            String level = levels[random.nextInt(levels.length)];

            // Simulate adding semi-structured key-value pairs at the end of the message
            String additionalInfo = String.format(" | service=%s | requestId=%s | durationMs=%d",
                    service,
                    java.util.UUID.randomUUID().toString().substring(0, 8),
                    random.nextInt(5000));

            switch (level) {
                case "INFO":
                    logger.info("{}{}", message, additionalInfo);
                    break;
                case "DEBUG":
                    logger.debug("{}{}", message, additionalInfo);
                    break;
                case "WARN":
                    logger.warn("{}{}", message, additionalInfo);
                    break;
                case "ERROR":
                    logger.error("{}{} | errorType=RuntimeException", message, additionalInfo, new RuntimeException("Simulated Error"));
                    break;
            }
        }
        System.out.println("Finished generating semi_structured_logs.log");
    }
}
