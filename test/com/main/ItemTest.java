package com.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bakermatic.Item;

import java.math.BigDecimal;


public class ItemTest {
    private Item testItem;
    private BigDecimal price;

    @BeforeEach
    public void setUp(){
        String name = "cream";
        int quantity = 5;
        this.price = new BigDecimal(0.25);
        this.testItem = new Item(name, quantity, price, "Cream");
    }

    @Test
    public void createsItem(){
        assertEquals("cream", testItem.getName());
        assertEquals(5, testItem.getQty());
        assertEquals(0,(this.price).compareTo(testItem.getPrice()));
    }

    @Test
    public void changesQty(){
        this.testItem.changeQty(-2);
        assertEquals(3, testItem.getQty());
    }
}
