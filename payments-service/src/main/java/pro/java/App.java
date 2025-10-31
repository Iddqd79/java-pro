package pro.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Home task-8
 *
 */
@SpringBootApplication
@EnableFeignClients
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class);

    }
}
