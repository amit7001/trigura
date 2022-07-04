package com.trafigura.equityposition.service;


import com.trafigura.equityposition.entity.Transaction;
import com.trafigura.equityposition.exception.ErrorCodeEnum;
import com.trafigura.equityposition.exception.CustomException;
import com.trafigura.equityposition.model.ActionEnum;
import com.trafigura.equityposition.model.TransactionDTO;
import com.trafigura.equityposition.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RuleComposite")
public class ValidateService {

    @Autowired
    private TransactionRepository repository;

    public TransactionDTO validate(TransactionDTO transaction) {
        //version check
        versionValidate(transaction);

        

        Transaction transaction1 = repository.save(Transaction.from(transaction));
        return TransactionDTO.from(transaction1);
    }

    private void insertCancelvalidate(TransactionDTO transaction) {
        if (transaction.getAction().equals(ActionEnum.INSERT)) {
            if (!(transaction.getVersion().compareTo(1) == 0))
                throw new CustomException(ErrorCodeEnum.INSERT__VERSION_ERROR);
            Integer minVersion = repository.findMinVersionByTradeId(transaction.getTradeId());
            if (null != minVersion && transaction.getVersion() > minVersion)
                throw new CustomException(ErrorCodeEnum.INSERT_CANCEL_ERROR);
        }
        if (transaction.getAction().equals(ActionEnum.CANCEL)) {
            Integer maxVersion = repository.findMaxVersionByTradeId(transaction.getTradeId());
            if (null != maxVersion && transaction.getVersion() < maxVersion)
                throw new CustomException(ErrorCodeEnum.INSERT_CANCEL_ERROR);
        }
        if (transaction.getAction().equals(ActionEnum.UPDATE)) {
            List<Transaction> list = repository.findByTradeId(transaction.getTradeId());
            if (transaction.getAction().equals(ActionEnum.UPDATE) && transaction.getVersion() <= 1) {
                throw new CustomException(ErrorCodeEnum.UPDATE_ERROR);
            }
        }
    }


    private void versionValidate(TransactionDTO transaction) {
        List<Transaction> list = repository.findByTradeIdAndVersion(transaction.getTradeId(), transaction.getVersion());
        if (!list.isEmpty()) {
            throw new CustomException(ErrorCodeEnum.SAME_VERSION_ERROR);
        }
    }

}
