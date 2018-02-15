package com.main;

import com.bakermatic.BakerLogic;
import com.bakermatic.Cake;
import com.bakermatic.Inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CakesTest {
    Cake testCake;
    Inventory currentInventory;

    @BeforeEach
    public void setUp(){
        this.currentInventory = new Inventory();
        BakerLogic baker = new BakerLogic(this.currentInventory);
        baker.LoadInventory();
        baker.loadInitialCakes();
        testCake = new Cake("coffee");
        testCake.ingredients.put("cream", 2);
        testCake.ingredients.put("coffee", 3);
    }

    @Test
    public void checksIfAvailable(){
        boolean available = testCake.isAvailable(this.currentInventory);
        assertTrue(available);
    }

}
