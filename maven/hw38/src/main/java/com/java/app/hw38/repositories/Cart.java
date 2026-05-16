package com.java.app.hw38.repositories;

import com.java.app.hw38.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private final List<Product> items = new ArrayList<>();

    public List<Product> getAllProducts() {
        return items;
    }

    public void addProduct(Product p) {
        items.add(p);
    }

    public boolean removeById(Long id) {
        return items.removeIf(p -> p.getId().equals(id));
    }


    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double total() {
        return items.stream().map(Product::getPrice).reduce(0d, Double::sum);
    }

    public void printContents() {
        if (isEmpty()) {
            System.out.println("  Кошик порожній.");
            return;
        }
        System.out.println("  ┌─────────────────────────────────────────────────────┐");
        items.forEach(p ->
                System.out.printf("  │  [%d] %-35s %10.2f грн  │%n",
                        p.getId(), p.getName(), p.getPrice()));
        System.out.println("  ├─────────────────────────────────────────────────────┤");
        System.out.printf("  │  Разом:  %42.2f грн  │%n", total());
        System.out.println("  └─────────────────────────────────────────────────────┘");
    }


}
