package com.java.app.hw38;

import com.java.app.hw38.repositories.Cart;
import com.java.app.hw38.repositories.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class TUI {

    private final ProductRepository productRepository;
    private final ApplicationContext context;

    private Cart cart;

    public TUI(ProductRepository productRepository, ApplicationContext context) {
        this.productRepository = productRepository;
        this.context = context;
    }

    public void run() {
        cart = context.getBean(Cart.class);
        Scanner scanner = new Scanner(System.in);
        printBanner();
        boolean running = true;
        while (running) {
            printMainMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> handleAddProduct(scanner);
                case "2" -> handleRemoveProduct(scanner);
                case "3" -> cart.printContents();
                case "4" -> printCatalog();
                case "5" -> {
                    cart = context.getBean(Cart.class);
                    System.out.println("  ✔  Кошик очищено");
                }
                case "0" -> {
                    System.out.println("\n  До побачення! 👋");
                    running = false;
                }
                default -> System.out.println("  ⚠  Невідома команда. Спробуйте ще раз.");
            }
        }

        scanner.close();
    }

    private void handleAddProduct(Scanner scanner) {
        printCatalog();
        System.out.print("  Введіть ID товару для додавання: ");
        try {
            long id = Long.parseLong(scanner.nextLine().trim());
            Product found = productRepository.findById(id);
            if (found != null) {
                cart.addProduct(found);
            } else {
                System.out.println("  ⚠  Товар з ID=" + id + " не знайдено.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  ⚠  Введіть числове значення ID.");
        }
    }

    private void handleRemoveProduct(Scanner scanner) {
        if (cart.isEmpty()) {
            System.out.println("  Кошик порожній — нічого видаляти.");
            return;
        }
        System.out.println("  Поточний вміст кошика:");
        cart.printContents();
        System.out.print("  Введіть ID товару для видалення: ");
        try {
            long id = Long.parseLong(scanner.nextLine().trim());
            boolean removed = cart.removeById(id);
            if (removed) {
                System.out.println("  ✔  Товар видалено з кошика.");
            } else {
                System.out.println("  ⚠  Товар з ID=" + id + " не знайдено в кошику.");
            }
        } catch (NumberFormatException e) {
            System.out.println("  ⚠  Введіть числове значення ID.");
        }
    }


    private void printCatalog() {
        List<Product> all = productRepository.findAll();
        System.out.println();
        System.out.println("  ╔═══ КАТАЛОГ ТОВАРІВ ══════════════════════════════════╗");
        all.forEach(p ->
                System.out.printf("  ║  [%d] %-35s %10.2f грн  ║%n",
                        p.getId(), p.getName(), p.getPrice()));
        System.out.println("  ╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // UI utils
    private void printBanner() {
        System.out.println("""
                
                  ╔══════════════════════════════════════╗
                  ║       🛒  ІНТЕРНЕТ-МАГАЗИН  🛒       ║
                  ╚══════════════════════════════════════╝
                """);
    }

    private void printMainMenu() {
        System.out.println("""
                ─────────────── МЕНЮ ───────────────
                [1] Додати товар до кошика
                [2] Видалити товар з кошика
                [3] Переглянути кошик
                [4] Переглянути каталог
                [5] Очистити кошик (новий екземпляр)
                [0] Вийти
                ────────────────────────────────────""");
        System.out.print("  Ваш вибір: ");
    }
}

