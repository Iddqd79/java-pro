package pro.java.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.java.clients.ExecutorFeignClientErrorDecoder;
import pro.java.clients.ExecutorFeignClientInterceptor;

@Configuration
public class ExecutorFeignClientConfiguration {
    @Bean
    public ExecutorFeignClientInterceptor executorFeignClientInterceptor() {
        return new ExecutorFeignClientInterceptor();
    }

    @Bean
    public ExecutorFeignClientErrorDecoder errorDecoder() {
        return new ExecutorFeignClientErrorDecoder();
    }
}
