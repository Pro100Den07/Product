package org.example;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "Electronics", 1200.0),
                new Product("Coffee Maker", "Appliances", 80.0),
                new Product("Headphones", "Electronics", 150.0),
                new Product("Blender", "Appliances", 50.0),
                new Product("T-Shirt", "Clothing", 25.0),
                new Product("Jeans", "Clothing", 50.0),
                new Product("Book", "Books", 15.0),
                new Product("Keyboard", "Electronics", 70.0)
        );

        // Згрупування продуктів за категоріями та обчислення середньої ціни
        Map<String, Double> averagePricesByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.averagingDouble(Product::getPrice)));

        // Округлення середньої ціни до одного десяткового знака
        averagePricesByCategory.replaceAll((category, averagePrice) -> Math.round(averagePrice * 10.0) / 10.0);

        // Знаходження категорії з найвищою середньою ціною
        String categoryWithHighestAveragePrice = averagePricesByCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Немає категорій");

        // Виведення результатів
        System.out.println("Середні ціни за категоріями:");
        averagePricesByCategory.forEach((category, averagePrice) -> System.out.println("  " + category + ": " + averagePrice));

        System.out.println("\nКатегорія з найвищою середньою ціною: " + categoryWithHighestAveragePrice);
    }
}