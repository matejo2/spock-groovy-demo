package com.matejo.spockgroovydemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/employees")
    public String getEmployees(){
        return "alles fit";
    }
}
