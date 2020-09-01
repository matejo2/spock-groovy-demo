package com.matejo.spockgroovydemo


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ApplicationTest extends Specification {

    Application application = new Application()

    def "application works"() {

        expect: "application starts"
        application != null
    }
}
