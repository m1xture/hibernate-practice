package com.java.app.hw39;

import java.util.Optional;

public interface CustomerDao {
    void create(Customer product);

    Optional<Customer> findById(Long id);

    void update(Customer product);

    void delete(Long id);

    void execute(String ddlSql);
}
