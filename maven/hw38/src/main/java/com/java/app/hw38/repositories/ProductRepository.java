package com.java.app.hw38.repositories;

import com.java.app.hw38.Product;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Repository
public class ProductRepository {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter;

    private List<Product> loadFromJson() {
        try (InputStream is = getClass().getResourceAsStream("/products.json")) {
            if (is == null) {
                log.error("products.json not found");
                return List.of();
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(is, new TypeReference<List<Product>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException("Error reading products.json: " + e.getMessage(), e);
        }
    }

    public ProductRepository() {
        List<Product> loaded = loadFromJson();
        log.info("Loaded {} products", loaded.size());
        products.addAll(loaded);
        long maxId = loaded.stream().mapToLong(Product::getId).max().orElse(0L);
        idCounter = new AtomicLong(maxId + 1);
        products.forEach(p -> System.out.println(p.toString()));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public Product save(String name, double price) {
        Product product = new Product();
        product.setId(idCounter.getAndIncrement());
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    public Product update(Long id, String newName, double newPrice) {
        Product product = findById(id);
        product.setName(newName);
        product.setPrice(newPrice);
        return product;
    }

    public Product deleteById(Long id) {
        Product deletedProduct = products.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (deletedProduct != null) {
            products.remove(deletedProduct);
        }
        return deletedProduct;
    }

}
