package com.matejo.spockgroovydemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(EmployeeController.class)
class JunitEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void basic_http_get() throws Exception {
        var url = "/employees";
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(1L, "ad", "asd"));
        when(service.getEmployees()).thenReturn(expectedEmployees);

        var response = mockMvc.perform(get(url)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(mapper.writeValueAsString(expectedEmployees));
    }

    @Test
    void get_specific_employee() throws Exception {
        var id = 1L;
        var url = String.format("/employees/%d", id);
        var employee = new Employee(id, "foo", "bar");
        when(service.getEmployeeById(id)).thenReturn(employee);

        var response = mockMvc.perform(get(url)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(mapper.writeValueAsString(employee));
    }

    @Test
    void not_getting_specific_employee_returns_no_content() throws Exception {
        var id = 1L;
        var url = String.format("/employees/%d", id);
        when(service.getEmployeeById(id)).thenReturn(null);

        var response = mockMvc.perform(get(url)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}
