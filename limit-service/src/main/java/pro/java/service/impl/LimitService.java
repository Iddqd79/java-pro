package pro.java.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.java.dto.LimitDTO;
import pro.java.dto.OperationDTO;
import pro.java.entity.Limit;
import pro.java.entity.Operation;
import pro.java.enums.OperationStatus;
import pro.java.mapper.LimitMapper;
import pro.java.mapper.OperationMapper;
import pro.java.repository.LimitRepository;
import pro.java.repository.OperationRepository;
import pro.java.service.ILimitService;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LimitService implements ILimitService {

    private final LimitRepository limitRepository;
    private final OperationRepository operationRepository;
    private final LimitMapper limitMapper;
    private final OperationMapper operationMapper;
    @Value("${limit.value}")
    private BigDecimal limit;

    @Override
    @Transactional
    public LimitDTO get(Long userId) {
        return limitMapper.convertToDTO(limitRepository.findById(userId).orElseGet(() -> {
            Limit emptyLimit = new Limit();
            emptyLimit.setUserId(userId);
            return limitRepository.saveAndFlush(emptyLimit);
        }));

    }

    @Override
    @Transactional
    public void hold(Long userId, BigDecimal amount) {
        Limit limit = limitRepository.findById(userId).orElseThrow(() -> new RuntimeException("Limit for user is absent"));
        if (limit.getLimit().compareTo(amount) < 0) {
            throw new RuntimeException("Today's limit is below zero, please wait tomorrow");
        }
        OperationDTO operationDTO = new OperationDTO(null, amount, OperationStatus.HOLD, userId);
        operationRepository.save(operationMapper.convertToEntity(operationDTO));
    }

    @Override
    public void decline(Long operationId) {
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new RuntimeException("Operation is absent"));
        operation.setStatus(OperationStatus.DECCLINE);
        operationRepository.save(operation);
    }

    @Override
    @Transactional
    public void accept(Long operationId) {
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new RuntimeException("Operation is absent"));
        Limit limit = limitRepository.findById(operation.getLimit().getUserId()).orElseThrow(() -> new RuntimeException("Limit for user is absent"));
        if (operation.getAmount().compareTo(limit.getLimit()) > 0) {
            operation.setStatus(OperationStatus.DECCLINE);
        } else {
            operation.setStatus(OperationStatus.ACCEPT);
            limit.setLimit(limit.getLimit().subtract(operation.getAmount()));
            limitRepository.save(limit);
        }
        operationRepository.save(operation);
    }

    @Override
    public void resetAllLimits() {
        limitRepository.resetAll(limit);
    }
}
