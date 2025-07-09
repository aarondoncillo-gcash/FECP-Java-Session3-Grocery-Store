package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static String viewInventory(HashMap<String, Integer> products) {
        // if no products in hashmap
        if (products.isEmpty()) {
            return "No products available. Please add a product first.\n";
        } else {
            StringBuilder sb = new StringBuilder();
            // print each product and quantity
            for (Map.Entry<String, Integer> entry : products.entrySet()) {
                sb.append(entry.getKey() + " - " + entry.getValue() +  ((entry.getValue() >= 2) ? " pcs\n" : " pc\n"));
            }

            return sb.toString();
        }
    }

    public static String addProduct(String name, int qty, HashMap<String, Integer> products) {
        // input quantity validations
        if (qty <= -1)
            return "Quantity must be non-negative\n";
        if(!checkProductIfExists(name, products)){
            // add data in hashmap
            products.put(capitalizeKeyName(name), qty);
            return "Product added!\n";
        } else {
            // if adding an existing product, will replace the old quantity value with a new one
            products.put(capitalizeKeyName(name), qty);
            return "Product already exist. Will update its quantity instead\n";
        }
    }

    public static String checkProduct(String name, HashMap<String, Integer> products) {
        // check if product name key exists in hashmap
        if(checkProductIfExists(name, products)){
            name = capitalizeKeyName(name);
            // get the value of the product key
            return name + " is in stock: " + products.get(name) + "\n";
        } else {
            // if user checks a non existing product
            return "Product not found.\n";
        }
    }

        public static String updateProduct(String name, int newQuantity, HashMap<String, Integer> products){
            // check if product name key exists in hashmap
        if(checkProductIfExists(name, products)){
            // input quantity validations
            if (newQuantity <= -1)
                return "Quantity must be non-negative\n";
            // update value of product key
            products.replace(capitalizeKeyName(name), newQuantity);
            return "Stock updated!\n";
        } else {
            // if user updates a non existing product
            return "Product not found.\n";
        }


    }

    public static String removeProduct(String name,  HashMap<String, Integer> products) {
        // check if an existing product can be removed
        if(checkProductIfExists(name, products)){
            //remove the key in hashmap
            products.remove(capitalizeKeyName(name));
            return "Product removed.\n";
        } else {
            return "Product not found.\n";
        }
    }

    public static boolean checkProductIfExists(String name, HashMap<String, Integer> products) {
        // check if key is in hashmap
        for (String key : products.keySet()){
            if(key.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public static String capitalizeKeyName(String str)
    {
        //Capitalize the string key in order to enforce case-insensitive key names
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer>  products = new HashMap<>();
        // add existing products
        products.put("Milk", 20);
        products.put("Bread", 15);
        products.put("Eggs", 30);

        int choice;
        boolean keepRunning = true;
        while(keepRunning) {
            System.out.println("--- Grocery Inventory Menu ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Product");
            System.out.println("3. Check Product");
            System.out.println("4. Update Stock");
            System.out.println("5. Remove Product");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            // Get user choice
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                //View Inventory
                case 1:
                    //print output message
                    System.out.println(viewInventory(products));
                    break;
                //Add Product
                case 2:
                {
                    System.out.print("Enter product name: ");
                    // get product and quantity
                    String productName = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();
                    //print output message
                    System.out.println(addProduct(productName, quantity, products));
                    break;
                }
                // Check Product
                case 3:
                {
                    System.out.print("Enter product name to check: ");
                    //get product
                    String productName = sc.nextLine();
                    //print output message
                    System.out.println(checkProduct(productName, products));
                    break;
                }
                // Update Stock
                case 4:
                {
                    System.out.print("Enter product name to update: ");
                    // get product name
                    String productName = sc.nextLine();
                    int newQuantity;
                    // get and validate input quantity
                    while(true) {
                        System.out.print("Enter new stock quantity: ");
                        newQuantity = sc.nextInt();
                        if (newQuantity >= 0 )
                            break;
                    }
                    sc.nextLine();
                    // print output message
                    System.out.println(updateProduct(productName, newQuantity, products));
                    break;
                }
                // Remove Product
                case 5: {
                    System.out.print("Enter product name to remove: ");
                    // get product name
                    String productName = sc.nextLine();
                    // print output message
                    System.out.println(removeProduct(productName, products));
                    break;
                }
                // Exit
                case 6:
                    //break loop and exit
                    keepRunning = false;
                    System.out.println("Exiting system");
                    break;

                default :
                    System.out.println("Invalid option. Try again\n");
            }

        }

    }
}