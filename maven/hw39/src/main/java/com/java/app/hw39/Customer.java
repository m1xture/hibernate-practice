package com.java.app.hw39;

import lombok.*;


@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String fullName;
    private String email;
    private int socialSecurityNumber;

    Customer(String fullName, String email, int socialSecurityNumber) {
        this.fullName = fullName;
        this.email = email;
        this.socialSecurityNumber = socialSecurityNumber;
    }

}
