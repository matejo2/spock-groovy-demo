package com.matejo.spockgroovydemo

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

    def "get employees returns ok"() {
        given: "the rest api"
        def url = "/employees"

        when: "rest api is called"
        def response = mockMvc.perform(MockMvcRequestBuilders.get(url)).andReturn().response

        then: "http status 200 ok was returned"
        response.getStatus() == HttpStatus.OK.value()
    }
}
