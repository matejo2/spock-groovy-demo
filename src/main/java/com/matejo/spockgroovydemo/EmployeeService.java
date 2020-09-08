package com.matejo.spockgroovydemo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> getEmployees(){
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id).get();
    }
}
