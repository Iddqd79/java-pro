package pro.java.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "limit.service.enable", havingValue = "true")
public class ResetLimitService {
    private final LimitService limitService;

    public ResetLimitService(LimitService limitService) {
        this.limitService = limitService;
    }

    @Scheduled(cron = "${limit.service.cron}")
    void resetLimit() {
        limitService.resetAllLimits();

    }
}
