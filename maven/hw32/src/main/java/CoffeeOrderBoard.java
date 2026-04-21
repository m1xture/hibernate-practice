import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CoffeeOrderBoard {
    private final Queue<Order> orders = new ConcurrentLinkedQueue<>();
    private final AtomicInteger lastOrderNumber = new AtomicInteger(0);

    public Order add(String customerName) {
        Order order = new Order(lastOrderNumber.incrementAndGet(), customerName);
        boolean res = orders.add(order);
        if (res) {
            log.info("Order added: {}", order);
        } else {
            log.error("Unable to add order: {}", order);
        }
        return res ? order : null;
    }

    public Order deliver() {
        Order order = orders.poll();
        if (order != null) {
            log.info("Order delivered: {}", order);
        } else {
            log.warn("Queue is empty, nothing to deliver");
        }
        return order;
    }

    public synchronized Order deliver(int orderNumber) {
        Order order = orders.stream().filter(order1 -> order1.getOrderNumber() == orderNumber).findFirst().orElse(null);
        if (order != null) {
            orders.remove(order);
            log.info("Order delivered: {}", order);
        } else {
            log.warn("Invalid order number: {}", orderNumber);
        }
        return order;
    }

    public void draw() {
        System.out.printf("There are %d orders\n", orders.size());
        System.out.println("┌─────────────────────────────┐");
        System.out.println("│       COFFEE ORDERS         │");
        System.out.println("├────────┬────────────────────┤");
        System.out.println("│ Number │ Customer           │");
        System.out.println("├────────┼────────────────────┤");

        if (orders.isEmpty()) {
            System.out.println("│        No orders yet        │");
        } else {
            orders.forEach((order) -> System.out.printf("│ #%-6d│ %-19s│%n", order.getOrderNumber(), order.getCustomerName()));
        }

        System.out.println("└────────┴────────────────────┘");
    }

}
