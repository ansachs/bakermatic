package com.main;

import com.bakermatic.Item;
import com.bakermatic.Inventory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;


public class TestInventory {
    private Item testItem1, testItem2;
    private Inventory testInventory;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        String name = "cream";
        int quantity = 5;
        BigDecimal price = new BigDecimal(0.25);
        this.testItem1 = new Item(name, quantity, price, "Cream");
        name = "sugar";
        quantity = 10;
        price = new BigDecimal(0.50);
        this.testItem2 = new Item(name, quantity, price, "Sugar");
        this.testInventory = new Inventory();
        this.testInventory.addItem(testItem1);
        this.testInventory.addItem(testItem2);
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void addItemToInventory(){
        assertEquals(this.testItem1, this.testInventory.getItem("cream"));
    }

    @Test
    public void printsInventory(){
        this.testInventory.printInventory();
        assertThat(outContent.toString(), containsString("Cream, 5 \n\n" +"Sugar, 10"));

    }

}
