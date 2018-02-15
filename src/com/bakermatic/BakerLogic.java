package com.bakermatic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

import com.google.gson.*;


public class BakerLogic {
    private HashMap<String, Cake> allCakes;
    private Inventory inventory;
    private ArrayList<Cake> availableCakes;

    public BakerLogic(Inventory inventory){
        this.inventory = inventory;
        this.allCakes = new HashMap<>();
        this.availableCakes = new ArrayList<>();
    }

    public void loadInitialCakes(){
        JsonObject rootObject = this.getRoot();
        JsonObject cakes = rootObject.getAsJsonObject("cakes");
        for(String cake: cakes.keySet()){
            JsonObject ingredients = cakes.getAsJsonObject(cake);
            Cake newCake = new Cake(cake);
            for(String item: ingredients.keySet()){
                newCake.ingredients.put(item, ingredients.get(item).getAsInt());
            }

            this.allCakes.put(cake, newCake);
        }
    }

    public void LoadInventory() {
        JsonObject rootObject = this.getRoot();
        JsonObject ingredients = rootObject.getAsJsonObject("inventory");
        for(String item: ingredients.keySet()){
            JsonObject properties = ingredients.getAsJsonObject(item);
            BigDecimal cost = properties.get("itemPrice").getAsBigDecimal();
            Item newItem = new Item(item, properties.get("itemQty").getAsInt(), cost, properties.get("written").getAsString());
            this.inventory.addItem(newItem);
        }

    }

    public boolean purchaseCake(Cake cake){
        if (cake.isAvailable(this.inventory)){
            for (String item : cake.ingredients.keySet()) {
                int reqAmount = cake.ingredients.get(item);
                this.inventory.getItem(item).changeQty(-reqAmount);
            }
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Cake> loadCakeOptions(){
        this.availableCakes.clear();
        for(String cake : this.allCakes.keySet()){
            boolean avail = this.allCakes.get(cake).isAvailable(this.inventory);
            if(avail){
                this.availableCakes.add(this.allCakes.get(cake));
            }
        }
        return this.availableCakes;
    }

    public int printCakeOptions(){
        System.out.println("Menu: \n");
        for(String cake : this.allCakes.keySet()){
            String number = this.availableCakes.contains(this.allCakes.get(cake)) ? Integer.toString(this.availableCakes.indexOf(this.allCakes.get(cake)) +1) : " ";
            String name = cake;
            BigDecimal cost = this.allCakes.get(cake).calcCost(this.inventory);
            boolean inStock = this.allCakes.get(cake).isAvailable(this.inventory);
            System.out.printf("%s, %s, $%s, %b \n\n", number, name, cost, inStock);
        }
        return this.availableCakes.size();
    }

    public void printAdditionalOptions(){
        int numCakes = this.allCakes.keySet().size();
        System.out.println("R' or 'r' - restock the inventory and redisplay the menu");
        System.out.println("'Q' or 'q' - quit the application");
        System.out.printf("[1-%s] - order the cake with the corresponding number in the menu", numCakes);
    }

    public void addCake(Cake cake){
        this.allCakes.put(cake.name, cake);
    }

    public Cake getCake(int cakeNum){
        return this.availableCakes.get(cakeNum);
    }

    private JsonObject getRoot(){
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("initial.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        return rootObject;
    }


}
