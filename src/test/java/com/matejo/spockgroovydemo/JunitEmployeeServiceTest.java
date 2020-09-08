package com.matejo.spockgroovydemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class JunitEmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService(repository);
    }

    @Test
    void getting_all_employees() {
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(1L, "we", "s"));
        expectedEmployees.add(new Employee(2L, "d", "4"));

        when(repository.findAll()).thenReturn(expectedEmployees);

        assertThat(service.getEmployees()).isEqualTo(expectedEmployees);
    }

    @Test
    void getting_employee_by_id() {
        var id = 32L;
        var expectedEmployee = new Employee(id, "Emily", "Bache");

        when(repository.findById(id)).thenReturn(Optional.of(expectedEmployee));

        assertThat(service.getEmployeeById(id)).isEqualTo(expectedEmployee);
    }

    @Test
    void not_getting_employee_by_id() {
        var id = 32L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThat(service.getEmployeeById(id)).isEqualTo(null);
    }
}
