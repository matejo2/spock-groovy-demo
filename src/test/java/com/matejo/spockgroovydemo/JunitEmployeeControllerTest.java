package com.matejo.spockgroovydemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class JunitEmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void basic_http_get() throws Exception {
        //given
        var url = "/employees";
        // when
        var response = mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk());

    }
}
