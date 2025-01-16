package se.mete.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main entry point for the Spring Boot application.
 * This class is used to bootstrap and launch the Spring Boot application.
 */
@SpringBootApplication // This annotation enables Spring Boot's auto-configuration, component scanning, and configuration of Spring beans.
public class DaifinCurrencyApiApplication {
    /**
     * The main method serves as the entry point of the application.
     * It launches the Spring Boot application by calling the `run` method of SpringApplication.
     *
     * @param args Command-line arguments (if any) passed to the application.
     */
    public static void main(String[] args) {
        // Starts the Spring Boot application, initializing the application context and running the embedded web server
        SpringApplication.run(DaifinCurrencyApiApplication.class, args);
    }
}
