package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GroceryStoreTest {

    private GroceryStore store;

    @BeforeEach
    void setUp() {
        store = new GroceryStore();
        store.initializeStock();
    }


    @Test
    void testAddNewItem_Success() {
        String result = store.addNewItem("Banana", 30);
        assertEquals("Product added!", result);
        assertTrue(store.getStock().containsKey("Banana"), "Stock should contain Banana.");
        assertEquals(30, store.getStock().get("Banana"), "Banana quantity should be 30.");
    }

    @Test
    void testAddNewItem_AlreadyExists() {
        String result = store.addNewItem("Milk", 10);
        assertEquals("Product already exists. Use 'Update Stock' to change the quantity.", result);
        assertEquals(20, store.getStock().get("Milk"), "Milk quantity should not have changed.");
    }

    @Test
    void testAddNewItem_WithQuantityZero() {
        String result = store.addNewItem("Mango", 0);
        assertEquals("Product added!", result);
        assertTrue(store.getStock().containsKey("Mango"), "Stock should contain Mango.");
        assertEquals(0, store.getStock().get("Mango"), "Mango quantity should be 0.");
    }

    @Test
    void testAddNewItem_WithNegativeQuantity() {
        String result = store.addNewItem("Apple", -5);
        assertEquals("Error: Quantity cannot be negative.", result);
        assertFalse(store.getStock().containsKey("Apple"), "Stock should not contain Apple.");
    }


    @Test
    void testFindItem_Exists() {
        String result = store.findItem("Milk");
        assertEquals("Milk is in stock: 20 pcs", result);
    }

    @Test
    void testFindItem_NotExists() {
        String result = store.findItem("Ice Cream");
        assertEquals("Ice Cream is not in the inventory.", result);
    }


    @Test
    void testUpdateItemCount_Success() {
        String result = store.updateItemCount("Bread", 25);
        assertEquals("Stock updated!", result);
        assertEquals(25, store.getStock().get("Bread"), "Bread quantity should be updated to 25.");
    }

    @Test
    void testUpdateItemCount_NotFound() {
        String result = store.updateItemCount("Tofu", 15);
        assertEquals("Product not found. Use 'Add Product' to add it to the inventory.", result);
    }

    @Test
    void testUpdateItemCount_OverwriteWithNewValue() {
        store.addNewItem("Milk", 50); // This will fail because it exists, let's update instead
        String result = store.updateItemCount("Milk", 50);
        assertEquals("Stock updated!", result);
        assertEquals(50, store.getStock().get("Milk"), "Milk quantity should be overwritten to 50.");
    }



    @Test
    void testRemoveItem_Success() {
        String result = store.removeItem("Eggs");
        assertEquals("Product removed.", result);
        assertFalse(store.getStock().containsKey("Eggs"), "Stock should no longer contain Eggs.");
        assertEquals(2, store.getStock().size(), "Stock size should be 2 after removal.");
    }

    @Test
    void testRemoveItem_NotFound() {
        String result = store.removeItem("Pizza");
        assertEquals("Product not found in inventory.", result);
        assertEquals(3, store.getStock().size(), "Stock size should remain 3.");
    }


    @Test
    void testGetStockDisplay_WithItems() {
        String display = store.getStockDisplay();
        assertTrue(display.contains("Milk - 20 pcs"));
        assertTrue(display.contains("Bread - 30 pcs"));
        assertTrue(display.contains("Eggs - 50 pcs"));
    }

    @Test
    void testGetStockDisplay_Empty() {
        store = new GroceryStore();
        String display = store.getStockDisplay();
        assertEquals("The inventory is currently empty.", display);
    }
}
