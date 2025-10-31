package pro.java.service;

import pro.java.dto.LimitDTO;

import java.math.BigDecimal;

public interface ILimitService {
    LimitDTO get(Long userId);

    void hold(Long user, BigDecimal amount);

    void decline(Long operationId);

    void accept(Long opertaionId);


    void resetAllLimits();

}
