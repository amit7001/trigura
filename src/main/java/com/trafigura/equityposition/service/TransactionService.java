package com.trafigura.equityposition.service;


import com.trafigura.equityposition.model.TransactionDTO;
import com.trafigura.equityposition.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.trafigura.equityposition.model.ActionEnum.CANCEL;
import static com.trafigura.equityposition.model.TransactionTypeEnum.BUY;
import static com.trafigura.equityposition.model.TransactionTypeEnum.SELL;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository repository;
    public Map<String, Object> calculate(TransactionDTO transaction) {
        log.info("start transaction quantity calculate");
        Map<String, Object> result = new HashMap<>();
        result.put("securityCode", transaction.getSecurityCode());
        if (transaction.getAction().equals(CANCEL)) {
            result.put("quantity", BigDecimal.ZERO);
            return result;
        }
        BigDecimal buy = repository.findQuantityBySecurityCodeAndType(transaction.getSecurityCode(), BUY.name(), CANCEL.name());
        BigDecimal sell = repository.findQuantityBySecurityCodeAndType(transaction.getSecurityCode(), SELL.name(), CANCEL.name());
        buy = buy == null ? BigDecimal.ZERO : buy;
        sell = sell == null ? BigDecimal.ZERO : sell;
        result.put("quantity", buy.subtract(sell).intValue());
        return result;
    }
}
