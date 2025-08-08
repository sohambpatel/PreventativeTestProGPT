package org.soham;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC; // Mapped Diagnostic Context for adding dynamic fields
import java.util.Random;
import java.util.UUID;

public class JavaStructuredLogs {

    private static final Logger logger = LoggerFactory.getLogger(JavaStructuredLogs.class);
    private static final Random random = new Random();

    public static void main(String[] args) {
        // Sample structured log entries (output to console and file via logback.xml)

        // Using MDC for contextual information (common in Java structured logging)
        MDC.put("service", "payment-processor");
        MDC.put("transactionId", UUID.randomUUID().toString());
        logger.info("Payment initiated successfully for order {}.", "ORD12345");
        MDC.remove("transactionId"); // Clear MDC if not needed for subsequent logs

        MDC.put("service", "user-management");
        MDC.put("userId", "user_987");
        logger.warn("Failed login attempt from IP {}. Reason: Invalid credentials.", "203.0.113.45");
        MDC.remove("userId");
        MDC.remove("service");

        try {
            int result = 10 / 0; // Simulate an error
        } catch (ArithmeticException e) {
            MDC.put("service", "calculator-service");
            MDC.put("operation", "division");
            logger.error("Arithmetic error occurred: {}", e.getMessage(), e); // 'e' includes stack trace
            MDC.clear(); // Clear all MDC entries
        }

        System.out.println("\n--- Generating a larger structured log file (structured_logs.jsonl) ---");
        // To generate 10 MB:
        // Adjust the loop count. Each log entry size varies.
        // Estimate average log entry size (e.g., 200-500 bytes per line).
        // 10 MB = 10 * 1024 * 1024 bytes = 10485760 bytes.
        // If avg is 250 bytes/line, you need ~42000 lines.
        for (int i = 0; i < 5000; i++) { // Adjust this number for target size
            MDC.put("service", random.nextBoolean() ? "api-gateway" : "data-ingestion");
            MDC.put("requestId", UUID.randomUUID().toString());
            MDC.put("userId", "user_" + random.nextInt(10000));
            String[] levels = {"INFO", "DEBUG", "WARN", "ERROR"};
            String level = levels[random.nextInt(levels.length)];

            switch (level) {
                case "INFO":
                    logger.info("Request processed successfully. Path: /api/v{}/resource", random.nextInt(3) + 1);
                    break;
                case "DEBUG":
                    MDC.put("payloadSize", String.valueOf(random.nextInt(2048)));
                    logger.debug("Debugging data for operation {}.", "op_" + i);
                    break;
                case "WARN":
                    MDC.put("retries", String.valueOf(random.nextInt(5) + 1));
                    logger.warn("External service responded with latency. Endpoint: {}", "https://external.com/api");
                    break;
                case "ERROR":
                    MDC.put("errorCode", "ERR" + random.nextInt(100));
                    logger.error("Database connection failed. Database: {}.", "users_db", new RuntimeException("DB Connection Pool Exhausted"));
                    break;
            }
            MDC.clear(); // Clear MDC for the next log entry
        }
        System.out.println("Finished generating structured_logs.jsonl");
    }
}