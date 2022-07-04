package com.trafigura.equityposition.model;


public enum ActionEnum {
   INSERT("INSERT"),UPDATE("UPDATE"),CANCEL("CANCEL");
   private String name;
   ActionEnum(String name) {
      this.name = name;
   }

}
