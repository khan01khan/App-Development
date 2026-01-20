package com.example.rms;

import java.util.ArrayList;

public class DataManager {
    public static ArrayList<MenuItem> menuItems = new ArrayList<>();
    public static ArrayList<CartItem> cart = new ArrayList<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    public static User currentUser = null;

    static {
        // Preload Menu Items with categories
        // We add "local" IDs for these initial items
        menuItems.add(new MenuItem("1", "Burger", 500, R.drawable.food_burger, "Fast Food"));
        menuItems.add(new MenuItem("2", "Cheese Pizza", 1200, R.drawable.food_pizza, "Fast Food"));
        menuItems.add(new MenuItem("3", "French Fries", 300, R.drawable.food_fries, "Sides"));
        menuItems.add(new MenuItem("4", "Grilled Sandwich", 400, R.drawable.food_sandwich, "Fast Food"));
        menuItems.add(new MenuItem("5", "Pasta Alfredo", 900, R.drawable.food_pasta, "Main Course"));
        menuItems.add(new MenuItem("6", "Chocolate Cake", 450, R.drawable.food_cake, "Dessert"));
        menuItems.add(new MenuItem("7", "Ice Cream", 350, R.drawable.food_icecream, "Dessert"));
        menuItems.add(new MenuItem("8", "Coffee", 250, R.drawable.food_coffee, "Beverages"));
        menuItems.add(new MenuItem("9", "Cold Drink", 150, R.drawable.food_coke, "Beverages"));
        menuItems.add(new MenuItem("10", "Caesar Salad", 200, R.drawable.food_salad, "Healthy"));
        menuItems.add(new MenuItem("11", "Chicken Wings", 500, R.drawable.food_chicken_wings, "Sides"));
        menuItems.add(new MenuItem("12", "Mojito", 150, R.drawable.food_mojito, "Beverages"));
        menuItems.add(new MenuItem("13", "Iced Tea", 150, R.drawable.food_icedtea, "Beverages"));
        menuItems.add(new MenuItem("14", "Garlic Bread", 250, R.drawable.food_garlicbread, "Sides"));
        menuItems.add(new MenuItem("15", "Brownie", 350, R.drawable.food_brownie, "Dessert"));
        menuItems.add(new MenuItem("16", "Cheesecake", 1500, R.drawable.food_cheesecake, "Dessert"));
    }

    public static ArrayList<MenuItem> getItemsByCategory(String category) {
        if (category == null || category.equals("All")) return menuItems;
        ArrayList<MenuItem> filtered = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filtered.add(item);
            }
        }
        return filtered;
    }
}
