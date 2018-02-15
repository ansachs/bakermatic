package com.main;

import com.bakermatic.BakerLogic;
import com.bakermatic.Cake;
import com.bakermatic.Inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;


public class BakerLogicTest {
    private BakerLogic baker;
    private Cake testCake;
    private Inventory currentInventory;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        this.currentInventory = new Inventory();
        this.baker = new BakerLogic(currentInventory);
        this.baker.LoadInventory();
        this.baker.loadInitialCakes();
        testCake = new Cake("coffee");
        testCake.ingredients.put("cream", 2);
        testCake.ingredients.put("coffee", 3);
    }

    @Test
    public void loadsAllCakes(){
        ArrayList<Cake> cakes = this.baker.loadCakeOptions();
        assertTrue(cakes.size() == 6);
    }

    @Test
    public void calculatesCost() {
        BigDecimal testCakeCost = testCake.calcCost(currentInventory);
        assertTrue(testCakeCost.equals(new BigDecimal(2.75)));
    }

    @Test
    public void printsCakeOptions(){
        this.baker.loadCakeOptions();
        System.setOut(new PrintStream(outContent));
        this.baker.printCakeOptions();
        assertThat(outContent.toString(), containsString("Caffe Americano, $3.30, true"));
    }

    @Test
    public void printsAdditionalOptions(){
        this.baker.loadCakeOptions();
        System.setOut(new PrintStream(outContent));
        this.baker.printAdditionalOptions();
        assertThat(outContent.toString(), containsString("order the cake with the corresponding number in the menu"));
    }

    @Test
    public void purchaseACake(){
        int initalCream = this.currentInventory.getItem("cream").getQty();
        int reqCream = testCake.ingredients.get("cream");
        this.baker.addCake(testCake);
        baker.purchaseCake(testCake);
        int finalCream = this.currentInventory.getItem("cream").getQty();
        assertTrue(initalCream - finalCream == reqCream);
    }

}
