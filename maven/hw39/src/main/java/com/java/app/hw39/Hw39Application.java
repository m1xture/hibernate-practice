package com.java.app.hw39;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class Hw39Application {

    public static void main(String[] args) {
        var ivan = new Customer("Ivan", "ivan@workemail.com", 23);
        var elena = new Customer("Elena", "elena@gmail.com", 55);

        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerDao dao = ctx.getBean(CustomerDao.class);

        dao.execute("""
                CREATE TABLE iF NOT EXISTS customer (
                    id BIGINT NOT NULL AUTO_INCREMENT,
                    fullName varchar(255),
                    email varchar(255),
                    socialSecurityNumber INT NOT NULL,
                    PRIMARY KEY (id)
                )
                """);

        dao.create(ivan);
        dao.create(elena);

        Customer c1 = dao.findById(1L).orElseThrow();
        Customer c2 = dao.findById(2L).orElseThrow();

        System.out.println("Found product by id=1, " + c2);
        System.out.println("Found product by id=2, " + c1);

        c1.setFullName("Peter");

        dao.update(c1);

        Customer updatedC1 = dao.findById(1L).orElseThrow();
        System.out.println("Found updated product by id=1, " + updatedC1);

        dao.delete(2L);
        Optional<Customer> deleted = dao.findById(2L);
        System.out.println("Search for deleted product returned " + (deleted.isPresent() ? deleted.get() : "empty result"));

        dao.execute("DROP TABLE customer");
    }


}
