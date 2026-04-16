void main() {
    Library library = new Library();

    Book b1 = new Book("1984", "G. Orwell");
    Book b2 = new Book("Mumu", "I. Turgenev");
    Book b3 = new Book("War and peace", "L. Tolstoy");
    Book b4 = new Book("Some Book", "Some Author");


    System.out.println("Is library empty? " + (library.getBookCount() == 0 ? "Yes" : "No"));

    library.addBook(b1);
    library.addBook(b2);
    library.addBook(b3);
    System.out.printf("After adding library has %d books inside\n", library.getBookCount());
    library.removeBook(b1);
    library.removeBook(b2);

    System.out.println("After removing first two books, here is what has left: " + library.getBooks().toString());

    System.out.printf("Result of removing book not from a lib: %b", library.removeBook(b4));

}
