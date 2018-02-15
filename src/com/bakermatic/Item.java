package com.bakermatic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Item {
    private String itemName;
    private int itemQty;
    private BigDecimal itemPrice;
    private String writtenAs;

    public Item(String name, int quantity, BigDecimal price, String written){
        this.itemName = name;
        this.itemQty = quantity;
        this.itemPrice = price;
        this.writtenAs = written;
    }

    public String getName(){
        return this.itemName;
    }

    public String getWritten(){
        return this.writtenAs;
    }

    public int getQty(){
        return this.itemQty;
    }

    public void changeQty(int quantity){
        this.itemQty = this.itemQty + quantity;
    }

    public BigDecimal getPrice(){
        return this.itemPrice;
    }

    @Override
    public String toString(){
    return "item: " + this.itemName + " quantity: " + this.itemQty + " price: " + this.itemPrice.setScale(2, RoundingMode.DOWN).toPlainString();

    }
}
