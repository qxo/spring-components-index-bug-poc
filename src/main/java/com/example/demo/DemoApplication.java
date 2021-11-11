package com.example.demo;

import java.net.URL;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import com.example.sub.TestCmp;
import com.other.TestOther;

@SpringBootApplication(scanBasePackages = { "com.example", "com.other" })
public class DemoApplication {
    /**
    * Logger for this class
    */
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

    public static void main(String[] args) {
        URL resource = DemoApplication.class.getResource("/META-INF/spring.components");
        LOGGER.info("===>{}", resource);
        // SpringApplication.run(DemoApplication.class, args);
        new SpringApplicationBuilder(DemoApplication.class).run(args);
    }

    /**
     * Handle application ready event.
     *
     * @param event the event
     */
    @EventListener
    public void handleApplicationReadyEvent(final ApplicationReadyEvent event) {
        LOGGER.info("Ready to process requests @ [{}]", Instant.ofEpochMilli(event.getTimestamp()));
        try {
            ConfigurableApplicationContext appContext = event.getApplicationContext();
            appContext.getBean(TestCmp.class);
            appContext.getBean(TestOther.class);
            Thread.sleep(5000L);
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
            Thread.currentThread().interrupt();
        } catch (RuntimeException ex) {
            LOGGER.error("failed: {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            System.exit(0);
        }
    }
}
