package string.randomizer;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RandomizerControllerIT {
    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnWorking_Job() throws Exception {
        MockHttpServletRequestBuilder getRequestBuilder = get("/random/jobs");
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnWorking_Results() throws Exception {

        MockHttpServletRequestBuilder getRequestBuilder = get("/random/results/output1-4-10");
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnWorking_NewJob() throws Exception {

        MockHttpServletRequestBuilder getRequestBuilder = post("/random/newJob?minlen=1&maxlen=4&alphabet=abcd&lines=10");
        mockMvc.perform(getRequestBuilder)
                .andDo(print())
                .andExpect(status().isOk());
    }

}
