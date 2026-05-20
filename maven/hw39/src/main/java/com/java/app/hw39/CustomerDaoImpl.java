package com.java.app.hw39;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private final JdbcOperations jdbcOperations;
    private final ResultSetExtractor<Customer> customerResultSetExtractor;

    public CustomerDaoImpl(final JdbcOperations jdbcOperations, final ResultSetExtractor<Customer> customerResultSetExtractor) {
        this.jdbcOperations = jdbcOperations;
        this.customerResultSetExtractor = customerResultSetExtractor;
    }

    @Override
    public void create(final Customer customer) {
        Objects.requireNonNull(customer, "Parameter [customer] must not be null!");
        int rowsInserted = this.jdbcOperations.update(
                "INSERT INTO customer (fullName, email, socialSecurityNumber) VALUES (?, ?, ?)",
                customer.getFullName(),
                customer.getEmail(),
                customer.getSocialSecurityNumber()
        );
        if (rowsInserted <= 0) {
            throw new RuntimeException("Failed to insert a customer %s".formatted(customer));
        }
    }

    @Override
    public Optional<Customer> findById(final Long id) {
        Objects.requireNonNull(id, "Parameter [id] must not be null!");
        Customer customer = this.jdbcOperations.query("SELECT * FROM customer WHERE id = ?", customerResultSetExtractor, id);
        return Optional.ofNullable(customer);
    }

    @Override
    public void update(final Customer customer) {
        if (customer == null || customer.getId() == null) {
            throw new IllegalArgumentException("Customer must not be null and an id must be set!");
        }
        int rowsUpdated = this.jdbcOperations.update("UPDATE customer SET email = ?, fullName = ? WHERE id = ?", customer.getEmail(), customer.getFullName(), customer.getId());
        if (rowsUpdated <= 0) {
            throw new RuntimeException("Failed to update a customer %s".formatted(customer));
        }
    }

    @Override
    public void delete(final Long id) {
        Objects.requireNonNull(id, "Parameter [id] must not be null!");
        int rowsDeleted = this.jdbcOperations.update("DELETE FROM customer WHERE id = ?", id);
        if (rowsDeleted <= 0) {
            throw new RuntimeException("Failed to delete customer with id=%d".formatted(id));
        }
    }

    @Override
    public void execute(final String ddlSql) {
        Objects.requireNonNull(ddlSql, "Parameter [ddlSql] must not be null!");
        this.jdbcOperations.execute(ddlSql);
    }

}
