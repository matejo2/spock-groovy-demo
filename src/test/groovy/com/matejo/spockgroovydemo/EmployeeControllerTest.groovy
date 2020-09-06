package com.matejo.spockgroovydemo

import com.fasterxml.jackson.databind.ObjectMapper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

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
        def response = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn().response

        then: "http status 200 ok was returned"
        response.getStatus() == HttpStatus.OK.value()
        and: "employee service was called"
        1 * service.getEmployees() >> expectedEmployees
        and: "response has expected employees"
        response.getContentAsString() == mapper.writeValueAsString(expectedEmployees)
    }
}
