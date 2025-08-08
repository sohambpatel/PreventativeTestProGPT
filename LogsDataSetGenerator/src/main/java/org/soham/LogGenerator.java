package org.soham;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Random;

public class LogGenerator {
    private static final String[] LEVELS = {"INFO", "WARN", "ERROR"};
    private static final String[] SERVICES = {"auth-service", "payment-service", "checkout-service"};
    private static final String[] MESSAGES = {
            "User login successful",
            "Database connection timeout",
            "NullPointerException at line 52",
            "Payment gateway returned 503",
            "Session expired for token abc123"
    };

    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("logs.jsonl");
        Random rand = new Random();

        for (int i = 0; i < 5000000; i++) {
            String log = String.format(
                    "{\"timestamp\":\"%s\",\"log_level\":\"%s\",\"service\":\"%s\",\"message\":\"%s\"}\n",
                    Instant.now().toString(),
                    LEVELS[rand.nextInt(LEVELS.length)],
                    SERVICES[rand.nextInt(SERVICES.length)],
                    MESSAGES[rand.nextInt(MESSAGES.length)]
            );
            writer.write(log);
        }
        writer.close();
        System.out.println("Log file generated.");
    }
}

