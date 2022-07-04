package com.trafigura.equityposition.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trafigura.equityposition.model.TransactionDTO;
import com.trafigura.equityposition.model.TransactionVO;
import com.trafigura.equityposition.service.TransactionService;
import com.trafigura.equityposition.service.ValidateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RequestMapping("/equity")
@RestController
@Slf4j
public class EquityPositionController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired()
    private ValidateService valdateService;
    @Autowired
    TransactionService service;

    @RequestMapping("/index")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }

    @RequestMapping("/transaction/input")
    public String input(@Validated TransactionVO transactionVO) throws JsonProcessingException {
        log.info("Equity Positions", transactionVO.toString());
        TransactionDTO dto = valdateService.validate(TransactionDTO.from(transactionVO));
        Map<String, Object> map = service.calculate(TransactionDTO.from(transactionVO));
        map.put("errorCode", HttpStatus.OK.value());
        map.put("reqData", dto);
        return objectMapper.writeValueAsString(map);
    }

}
