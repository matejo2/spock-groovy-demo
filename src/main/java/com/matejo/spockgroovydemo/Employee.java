package com.matejo.spockgroovydemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @javax.persistence.Id
    private Long id;
    private String lastName;
    private String firstName;
}
