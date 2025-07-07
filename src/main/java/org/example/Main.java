package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static String viewInventory(HashMap<String, Integer> products) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            sb.append(entry.getKey() + " - " + entry.getValue() +  ((entry.getValue() >= 2) ? " pcs\n" : " pc\n"));
        }

        return sb.toString();

    }

    public static String addProduct(String name, int qty, HashMap<String, Integer> products) {
        if(!checkProductIfExists(name, products)){
            products.put(name, qty);
            return "Product added!";
        } else {
            return "Product already exist";
        }
    }

    public static int checkProduct(String name, HashMap<String, Integer> products) {
        if(checkProductIfExists(name, products)){
            return products.get(name);
        } else {
            return -1;
        }
    }

    public static String updateProduct(String name, int newQuantity, HashMap<String, Integer> products){
        if(checkProductIfExists(name, products)){
            products.replace(name, newQuantity);
            return "Stock updated!";
        } else {
            return "Stock not updated";
        }


    }

    public static String removeProduct(String name,  HashMap<String, Integer> products) {
        if(checkProductIfExists(name, products)){
            products.remove(name);
            return "Product removed.";
        } else {
            return "Product doesn't exist";
        }
    }

    public static boolean checkProductIfExists(String name, HashMap<String, Integer> products) {
        for (String key : products.keySet()){
            if(key.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public static String capitalize(String str)
    {
        if(str == null || str.length()<=1) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer>  products = new HashMap<>();
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
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                {
                    if(products.isEmpty()) {
                        System.out.print("No products available");
                    } else {
                        System.out.println(viewInventory(products));
                    }
                    break;
                }
                case 2:
                {
                    System.out.print("Enter product name: ");
                    String productName = capitalize(sc.nextLine());
                    if(!checkProductIfExists(productName, products)) {
                        System.out.print("Enter quantity: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();
                        System.out.println(addProduct(productName, quantity, products));
                    } else {
                        System.out.println("Product already exist");
                    }
                    break;
                }
                case 3:
                {
                    System.out.print("Enter product name to check: ");
                    String productName = capitalize(sc.nextLine());
                    Integer currentStock = checkProduct(productName, products);
                    if (currentStock <= -1)
                        System.out.println("Product doesn't exist");
                    else {
                        System.out.println(capitalize(productName) + " is in stock: " + currentStock);
                    }
                    break;

                }
                case 4:
                {
                    System.out.print("Enter product name to update: ");
                    String productName = capitalize(sc.nextLine());
                    int newQuantity;

                    if(checkProductIfExists(productName, products)) {
                        while(true) {
                            System.out.print("Enter new stock quantity: ");
                            newQuantity = sc.nextInt();
                            if (newQuantity >= 0 )
                                break;
                        }
                        sc.nextLine();
                        System.out.println(updateProduct(productName, newQuantity, products));

                    } else {
                        System.out.println("Product doesn't exist");
                    }
                    break;


                }
                case 5: {
                    System.out.print("Enter product name to remove: ");
                    String productName = capitalize(sc.nextLine());
                    System.out.println(removeProduct(productName, products));
                    break;
                }

                default :
                    keepRunning = false;
                    System.out.println("Exiting system");
                    break;
            }

        }

    }
}