package com.matejo.spockgroovydemo

import org.spockframework.spring.SpringBean
import spock.lang.Specification

class EmployeeServiceTest extends Specification {

    @SpringBean
    EmployeeRepository repository = Mock()

    // alternativel: @SpringBootTest but this is not an integration test
    private EmployeeService service = new EmployeeService(repository)

    def "getting all employees from repo"() {
        given: "expected list of 2 employees"
        def expectedEmployees = [new Employee(1L, "foo", "ba"),
                                 new Employee(2L, "wonder", "woman")]

        when: "employees are called"
        def response = service.getEmployees()

        then: "expected employees are returned"
        response == expectedEmployees
        and: "repository was called"
        1 * repository.findAll() >> expectedEmployees
    }
}
