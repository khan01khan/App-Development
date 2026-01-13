package com.example.rms;

import java.util.ArrayList;

public class DataManager {
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<MenuItem> menuItems = new ArrayList<>();
    public static ArrayList<CartItem> cart = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    public static User currentUser = null;

    static {
        // Add Admin account
        users.add(new User("admin", "123", true));

        // Preload Menu Items with categories
        menuItems.add(new MenuItem("Burger", 500, R.drawable.food_burger, "Fast Food"));
        menuItems.add(new MenuItem("Cheese Pizza", 1200, R.drawable.food_pizza, "Fast Food"));
        menuItems.add(new MenuItem("French Fries", 300, R.drawable.food_fries, "Sides"));
        menuItems.add(new MenuItem("Grilled Sandwich", 400, R.drawable.food_sandwich, "Fast Food"));
        menuItems.add(new MenuItem("Pasta Alfredo", 900, R.drawable.food_pasta, "Main Course"));
        menuItems.add(new MenuItem("Chocolate Cake", 450, R.drawable.food_cake, "Dessert"));
        menuItems.add(new MenuItem("Ice Cream", 350, R.drawable.food_icecream, "Dessert"));
        menuItems.add(new MenuItem("Coffee", 250, R.drawable.food_coffee, "Beverages"));
        menuItems.add(new MenuItem("Cold Drink", 150, R.drawable.food_coke, "Beverages"));
        menuItems.add(new MenuItem("Caesar Salad", 200, R.drawable.food_salad, "Healthy"));
        menuItems.add(new MenuItem("Chicken Wings", 500, R.drawable.food_chicken_wings, "Sides"));
        menuItems.add(new MenuItem("Mojito", 150, R.drawable.food_mojito, "Beverages"));
        menuItems.add(new MenuItem("Iced Tea", 150, R.drawable.food_icedtea, "Beverages"));
        menuItems.add(new MenuItem("Garlic Bread", 250, R.drawable.food_garlicbread, "Sides"));
        menuItems.add(new MenuItem("Brownie", 350, R.drawable.food_brownie, "Dessert"));
        menuItems.add(new MenuItem("Cheesecake", 1500, R.drawable.food_cheesecake, "Dessert"));
    }

    public static User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean signup(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return false; // User already exists
            }
        }
        users.add(new User(username, password, false));
        return true;
    }

    public static ArrayList<MenuItem> getItemsByCategory(String category) {
        if (category.equals("All")) return menuItems;
        ArrayList<MenuItem> filtered = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filtered.add(item);
            }
        }
        return filtered;
    }
}
