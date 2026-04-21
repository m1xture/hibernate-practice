void main() {
    var orderBoard = new CoffeeOrderBoard();
    orderBoard.draw();

    Order order1 = orderBoard.add("John Snow");
    Order order2 = orderBoard.add("Jane Doe");
    Order order3 = orderBoard.add("Ivan Smith");
    orderBoard.draw();

    orderBoard.deliver();
    orderBoard.draw();

    // Trying to get invalid order
    orderBoard.deliver(order1.getOrderNumber());
    orderBoard.draw();

    // Default case
    orderBoard.deliver(order3.getOrderNumber());
    orderBoard.draw();

}
