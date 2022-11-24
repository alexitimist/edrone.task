package string.randomizer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfig {
    @Bean(name = "threadPoolExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executorPool = new ThreadPoolTaskExecutor();
        executorPool.setCorePoolSize(7);
        executorPool.setMaxPoolSize(10);
        executorPool.setQueueCapacity(10);
        executorPool.setAwaitTerminationSeconds(60);
        executorPool.initialize();
        return executorPool;
    }
}