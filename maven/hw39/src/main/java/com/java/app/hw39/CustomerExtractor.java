package com.java.app.hw39;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerExtractor implements ResultSetExtractor<Customer> {

    @Override
    public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
        Customer customer = new Customer();
        if (rs.next()) {
            customer.setId(rs.getLong("id"));
            customer.setFullName(rs.getString("fullName"));
            customer.setEmail(rs.getString("email"));
            customer.setSocialSecurityNumber(rs.getInt("socialSecurityNumber"));
        }
        return customer;
    }
}