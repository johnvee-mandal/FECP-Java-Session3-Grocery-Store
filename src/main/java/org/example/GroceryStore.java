package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GroceryStore {

    private Map<String, Integer> stock;
    public GroceryStore() {
        stock = new HashMap<>();
    }

    public void initializeStock() {
        stock.put("Milk", 20);
        stock.put("Bread", 30);
        stock.put("Eggs", 50);
    }

    public Map<String, Integer> getStock() {
        return stock;
    }

    public String getStockDisplay() {
        if (stock.isEmpty()) {
            return "The inventory is currently empty.";
        }
        return stock.entrySet().stream()
                .map(entry -> entry.getKey() + " - " + entry.getValue() + " pcs")
                .collect(Collectors.joining("\n"));
    }


    public String addNewItem(String productName, int quantity) {
        if (productName == null || productName.trim().isEmpty()) {
            return "Error: Product name cannot be empty.";
        }
        if (quantity < 0) {
            return "Error: Quantity cannot be negative.";
        }
        if (stock.containsKey(productName)) {
            return "Product already exists. Use 'Update Stock' to change the quantity.";
        }
        stock.put(productName, quantity);
        return "Product added!";
    }

    public String findItem(String productName) {
        if (stock.containsKey(productName)) {
            return productName + " is in stock: " + stock.get(productName) + " pcs";
        } else {
            return productName + " is not in the inventory.";
        }
    }


    public String updateItemCount(String productName, int newQuantity) {
        if (newQuantity < 0) {
            return "Error: Quantity cannot be negative.";
        }
        if (stock.containsKey(productName)) {
            stock.put(productName, newQuantity);
            return "Stock updated!";
        } else {
            return "Product not found. Use 'Add Product' to add it to the inventory.";
        }
    }

    public String removeItem(String productName) {
        if (stock.containsKey(productName)) {
            stock.remove(productName);
            return "Product removed.";
        } else {
            return "Product not found in inventory.";
        }
    }


    public static void displayMenu() {
        System.out.println("\n--- Grocery Inventory Menu ---");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Product");
        System.out.println("3. Check Product");
        System.out.println("4. Update Stock");
        System.out.println("5. Remove Product");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        GroceryStore store = new GroceryStore();
        store.initializeStock();
        Scanner input = new Scanner(System.in);
        boolean keepMenuOpen = true;

        while (keepMenuOpen) {
            displayMenu();
            int userSelection = -1;

            if (input.hasNextInt()) {
                userSelection = input.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
                continue;
            }
            input.nextLine();

            switch (userSelection) {
                case 1:
                    System.out.println("\nCurrent Inventory:");
                    System.out.println(store.getStockDisplay());
                    break;
                case 2:
                    System.out.print("Enter product name: ");
                    String newName = input.nextLine();
                    System.out.print("Enter quantity: ");
                    int newQty = input.nextInt();
                    input.nextLine();
                    System.out.println(store.addNewItem(newName, newQty));
                    break;
                case 3:
                    System.out.print("Enter product name to check: ");
                    String findName = input.nextLine();
                    System.out.println(store.findItem(findName));
                    break;
                case 4:
                    System.out.print("Enter product name to update: ");
                    String updateName = input.nextLine();
                    System.out.print("Enter new stock quantity: ");
                    int updateQty = input.nextInt();
                    input.nextLine();
                    System.out.println(store.updateItemCount(updateName, updateQty));
                    break;
                case 5:
                    System.out.print("Enter product name to remove: ");
                    String removeName = input.nextLine();
                    System.out.println(store.removeItem(removeName));
                    break;
                case 6:
                    System.out.println("Exiting system...");
                    keepMenuOpen = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 6.");
            }
        }
        input.close();
    }
}
