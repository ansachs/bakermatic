
package com.bakermatic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;


public class Cake {
    public String name;
    public HashMap<String, Integer> ingredients;
    private BigDecimal itemCost;

    public Cake(String name){
        this.name = name;
        this.ingredients = new HashMap<String, Integer>();
    }

    public boolean isAvailable(Inventory inventory){
        for(String name: this.ingredients.keySet()){
            int itemQty = inventory.getItem(name).getQty();
            if (itemQty == 0) {
                return false;
            } else if (itemQty < this.ingredients.get(name)){
                return false;
            }
        }
        return true;
    }

    public BigDecimal calcCost(Inventory inventory){
        this.itemCost = new BigDecimal(0);
        for(String ingredient: this.ingredients.keySet()){
            BigDecimal qty = new BigDecimal(ingredients.get(ingredient));
            BigDecimal cost = qty.multiply(inventory.getItem(ingredient).getPrice());
            this.itemCost = this.itemCost.add(cost);
        }
        return this.itemCost.setScale(2, RoundingMode.DOWN);
    }

}
