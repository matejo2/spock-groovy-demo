package com.matejo.spockgroovydemo;

import lombok.*;

@Data
@AllArgsConstructor
public class Employee {

    @javax.persistence.Id
    public Long Id;
    public String lastName;
    public String firstName;
}
