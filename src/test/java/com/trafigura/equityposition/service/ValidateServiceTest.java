package com.trafigura.equityposition.service;


import com.trafigura.equityposition.entity.Transaction;
import com.trafigura.equityposition.exception.CustomException;
import com.trafigura.equityposition.model.ActionEnum;
import com.trafigura.equityposition.model.TransactionDTO;
import com.trafigura.equityposition.model.TransactionTypeEnum;
import com.trafigura.equityposition.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ValidateServiceTest {
    private final static Logger logger = LoggerFactory.getLogger(ValidateServiceTest.class);

    @Autowired
    private TransactionRepository repository;
    @Autowired
    @Qualifier("InsertCancelRule")
    private ValidateService validateService;
    private TransactionDTO t1;
    private TransactionDTO t2;

    @Before
    public void prepareData() throws Exception {
        t1 = new TransactionDTO();
        t1.setQuantity(BigDecimal.TEN);
        t1.setAction(ActionEnum.INSERT);
        t1.setTradeId(111);
        t1.setVersion(0);
        t1.setType(TransactionTypeEnum.BUY);
        t1.setSecurityCode("REL");
        Transaction result = repository.save(Transaction.from(t1));
        logger.info("prepare data for position calculate test{}" + result);

    }


    @Test(expected = CustomException.class)
    public void testValidate1() throws Exception {
        t2 = new TransactionDTO();
        t2.setQuantity(BigDecimal.ONE);
        t2.setAction(ActionEnum.INSERT);
        t2.setTradeId(111);
        t2.setVersion(1);
        t2.setType(TransactionTypeEnum.SELL);
        t2.setSecurityCode("REL");
        validateService.validate(t2);
    }

    @Before
    public void prepareData1() throws Exception {
        t1 = new TransactionDTO();
        t1.setQuantity(BigDecimal.TEN);
        t1.setAction(ActionEnum.INSERT);
        t1.setTradeId(1);
        t1.setVersion(1);
        t1.setType(TransactionTypeEnum.BUY);
        t1.setSecurityCode("REL");
        Transaction result = repository.save(Transaction.from(t1));
        logger.info("prepare data for position calculate test{}" + result);

    }


    @Test(expected = CustomException.class)
    public void testValidate() throws Exception {
        t1.setAction(ActionEnum.UPDATE);
        t1.setQuantity(BigDecimal.valueOf(100));
        t1.setType(TransactionTypeEnum.SELL);
        validateService.validate(t1);
    }

    @Before
    public void prepareData2() throws Exception {
        t1 = new TransactionDTO();
        t1.setQuantity(BigDecimal.TEN);
        t1.setAction(ActionEnum.INSERT);
        t1.setTradeId(123);
        t1.setVersion(0);
        t1.setType(TransactionTypeEnum.BUY);
        t1.setSecurityCode("REL");
        Transaction result = repository.save(Transaction.from(t1));
        logger.info("prepare data for position calculate test{}" + result);

    }


    @Test(expected = CustomException.class)
    public void testValidate2() throws Exception {
        t1.setAction(ActionEnum.UPDATE);
        validateService.validate(t1);
    }
}