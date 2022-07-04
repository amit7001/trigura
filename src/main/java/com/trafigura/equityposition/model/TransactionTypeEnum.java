package com.trafigura.equityposition.model;


public enum TransactionTypeEnum {
   BUY("BUY"),SELL("SELL");
   private String name;
   TransactionTypeEnum(String name) {
      this.name = name;
   }
}
