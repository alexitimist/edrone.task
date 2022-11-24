package string.randomizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RandomizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RandomizerApplication.class, args);
    }

}
