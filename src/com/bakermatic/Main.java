package com.bakermatic;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Inventory currentInventory = new Inventory();
        BakerLogic baker = new BakerLogic(currentInventory);
        baker.LoadInventory();
        baker.loadInitialCakes();
        baker.loadCakeOptions();
        String response = "";
        while(! response.equalsIgnoreCase("q")){
            System.out.println("\n");
            currentInventory.printInventory();
            int noCakes = baker.printCakeOptions();
            Scanner reader = new Scanner(System.in);
            System.out.println("\n");
            response = reader.nextLine();
            if (response.equalsIgnoreCase("r")){
                currentInventory.resetInventory();
                baker.LoadInventory();
            } else if (response.equalsIgnoreCase("q")) {
            } else {
                try {
                    if (Integer.parseInt(response) >= 0 && Integer.parseInt(response) <= noCakes) {
                        Cake cake = baker.getCake(Integer.parseInt(response) -1);
                        boolean available = baker.purchaseCake(cake);
                        if (!available){
                            System.out.printf("out of stock: %s \n", cake.name);
                        } else {
                            System.out.printf("Dispensing: %s \n", cake.name);
                        }
                    } else {
                        System.out.printf("invalid selection: %s", response);
                    }
                } catch(Exception e) {
                    System.out.printf("invalid selection: %s", response);
                }
            }
        }
        System.out.println("thank you for your business");

    }


}

