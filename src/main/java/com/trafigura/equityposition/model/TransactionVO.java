package com.trafigura.equityposition.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVO {

    private Long transactionID;
    @NotNull
    private Integer tradeId;
    @NotNull
    private Integer version;
    @NotNull
    private String securityCode;
    @NotNull
    private String quantity;
    @NotNull
    private String action;
    @NotNull
    private String type;
    public static TransactionVO from(TransactionDTO dto){
        TransactionVO vo = new TransactionVO();
        vo.setAction(dto.getAction().name());
        vo.setType(dto.getType().name());
        vo.setVersion(dto.getVersion());
        vo.setTradeId(dto.getTradeId());
        vo.setQuantity(String.valueOf(dto.getQuantity()));
        vo.setSecurityCode(dto.getSecurityCode());
        return vo;
    }
}