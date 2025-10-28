package pro.java.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.java.clients.ExecutorFeignClient;

@Service
@ConditionalOnProperty(name = "limit.service.enable", havingValue = "true")
public class ResetLimitService {


    private final ExecutorFeignClient executorFeignClient;

    public ResetLimitService(ExecutorFeignClient executorFeignClient) {
        this.executorFeignClient = executorFeignClient;
    }

    @Scheduled(cron = "${limit.service.cron}")
    void resetLimit() {
        executorFeignClient.resetLimit();

    }
}
