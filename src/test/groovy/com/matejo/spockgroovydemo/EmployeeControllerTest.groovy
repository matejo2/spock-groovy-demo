package com.matejo.spockgroovydemo

import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@WebMvcTest
class EmployeeControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc

    @SpringBean
    EmployeeService service = Mock()

    ObjectMapper mapper = new ObjectMapper()

    def "get employees returns employees"() {
        given: "the rest api"
        def url = "/employees"
        and: "a list of employees"
        def expectedEmployees = [new Employee(1L, "Hopper", "Grace")]

        when: "rest api is called"
        def response = mockMvc.perform(get(url)).andReturn().response

        then: "http status 200 ok was returned"
        response.getStatus() == HttpStatus.OK.value()
        and: "employee service was called"
        1 * service.getEmployees() >> expectedEmployees
        and: "response has expected employees"
        response.getContentAsString() == mapper.writeValueAsString(expectedEmployees)
    }

    def "get single employee"() {
        given: "an employee"
        def id = 1L
        def employee = new Employee(id, "Lo", "Fi")
        def url = "/employees/$id"

        when: "employee is gotten"
        def response = mockMvc.perform(get(url)).andReturn().response

        then: "employee was found"
        response.getContentAsString() == mapper.writeValueAsString(employee)
        and: "http status is ok"
        response.status == HttpStatus.OK.value()
        and: "service was called"
        1 * service.getEmployeeById(id) >> employee
    }
}
