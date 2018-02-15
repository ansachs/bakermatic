package com.bakermatic;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    public Map<String, Item> currentInventory;
    private Item item;

    public Inventory(){
        this.currentInventory = new HashMap<String, Item>();
    }

    public void addItem(Item item){
        this.currentInventory.put(item.getName(), item);
    }

    public Item getItem(String name){
        return this.currentInventory.get(name);
    }

    public int getQty(){
        return this.currentInventory.size();
    }

    public void printInventory(){
        System.out.println("Inventory: \n");
        this.currentInventory.forEach((name, item)->{
            System.out.printf("%s, %s \n\n", item.getWritten(), item.getQty());
        });
    }

    public void resetInventory(){
        this.currentInventory.clear();
    }
}
