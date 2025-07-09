package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private HashMap<String, Integer> products;

    @BeforeEach
    void setup() {
        products= new HashMap<>();
        products.put("Milk", 20);
        products.put("Bread", 15);
        products.put("Eggs", 30);
        Main.viewInventory(products);
    }

    @Test
    void testViewNonEmptyInventory() {
        String expected = "Eggs - 30 pcs\nMilk - 20 pcs\nBread - 15 pcs\n";
        assertEquals(expected, Main.viewInventory(products));
    }

    @Test
    void testViewEmptyInventory() {
        products.clear();
        assertEquals("No products available. Please add a product first.\n", Main.viewInventory(products));
    }

    @Test
    void testAddValidProductWithValidQuantity() {
        String testName = "Banana";
        int testQuantity = 30;
        assertEquals("Product added!\n", Main.addProduct(testName, testQuantity, products));
        assertTrue(products.containsKey(testName));
        assertEquals(testQuantity, products.get(testName));
    }

    @Test
    void testAddValidProductWithNegativeQuantity() {
        String testName = "Bacon";
        int testQuantity = -5;
        assertEquals("Quantity must be non-negative\n", Main.addProduct(testName, testQuantity, products));
        assertFalse(products.containsKey(testName));
        assertFalse(products.containsValue(testQuantity));
    }

    @Test
    void testAddValidProductWithZeroQuantity() {
        String testName = "Mango";
        int testQuantity = 0;
        assertEquals("Product added!\n", Main.addProduct(testName, testQuantity, products));
        assertTrue(products.containsKey(testName));
        assertEquals(testQuantity, products.get(testName));
    }

    @Test
    void testAddExistingProduct() {
        String testName = "Milk";
        int testQuantity = 50;
        assertEquals("Product already exist. Will update its quantity instead\n", Main.addProduct(testName, testQuantity, products));
        assertTrue(products.containsKey(testName));
        assertEquals(testQuantity, products.get(testName));
    }

    @Test
    void testCheckExistingProduct() {
        String testName = "Milk";
        int expectedQuantity = 20;
        assertEquals("Milk is in stock: 20\n", Main.checkProduct(testName, products));
        assertTrue(products.containsKey(testName));
        assertEquals(expectedQuantity, products.get(testName));

    }

    @Test
    void testCheckNonExistingProduct() {
        String testName = "Ice Cream";
        assertEquals("Product not found.\n", Main.checkProduct(testName, products));
        assertFalse(products.containsKey(testName));
        assertNull(products.get(testName));

    }

    @Test
    void testUpdateExistingProductWithValidQuantity() {
        String testName = "Bread";
        int testQuantity = 25;
        assertEquals("Stock updated!\n", Main.updateProduct(testName, testQuantity, products));
        assertEquals(testQuantity, products.get(testName));
    }

    @Test
    void testUpdateExistingProductWithNegativeQuantity() {
        String testName = "Chips";
        int testQuantity = 100;
        products.put(testName, testQuantity);
        assertEquals("Quantity must be non-negative\n", Main.updateProduct(testName, -123, products));
        assertEquals(testQuantity, products.get(testName));
    }

    @Test
    void testUpdateNonExistingProduct() {
        String testName = "Tofu";
        int testQuantity = 456;
        assertEquals("Product not found.\n", Main.updateProduct(testName, testQuantity, products));
        assertFalse(products.containsKey(testName));
        assertNull(products.get(testName));
    }

    @Test
    void testRemoveExistingProduct() {
        String testName = "Eggs";
        assertEquals("Product removed.\n", Main.removeProduct(testName, products));
        assertFalse(products.containsKey(testName));
        assertNull(products.get(testName));
    }

    @Test
    void testRemoveNonExistingProduct() {
        String testName = "Pizza";
        assertEquals("Product not found.\n", Main.removeProduct(testName, products));
        assertFalse(products.containsKey(testName));
    }


}