package com.trafigura.equityposition.entity;


import com.trafigura.equityposition.model.ActionEnum;
import com.trafigura.equityposition.model.TransactionDTO;
import com.trafigura.equityposition.model.TransactionTypeEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "t_transactions",indexes = {@Index(columnList = "tradeId")},
uniqueConstraints = @UniqueConstraint(columnNames = {"tradeId","version"}))
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long transactionId;
    @Column(nullable = false)
    private Integer tradeId;
    @Column(nullable = false)
    private Integer version;
    @Column(nullable = false)
    private String securityCode;
    @Column(nullable = false)
    private BigDecimal quantity;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ActionEnum action;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum type;

    public static Transaction from(TransactionDTO transactionDTO){
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDTO,transaction);
        return transaction;
    }

}