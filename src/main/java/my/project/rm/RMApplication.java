package my.project.rm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RMApplication {

    public static void main(String[] args) {
        SpringApplication.run(RMApplication.class, args);
    }

}
