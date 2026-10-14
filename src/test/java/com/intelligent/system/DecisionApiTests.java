package com.intelligent.system;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:decisiontests;DB_CLOSE_DELAY=-1")
@AutoConfigureMockMvc
class DecisionApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createsAndListsDecision() throws Exception {
        mockMvc.perform(post("/api/decisions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {"title":"Budget review","context":"Cost forecast requires a careful review."}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Budget review"))
                .andExpect(jsonPath("$.recommendation").value("Run a cost-benefit review before proceeding"));

        mockMvc.perform(get("/api/decisions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Budget review"));
    }

    @Test
    void rejectsBlankDecisionTitle() throws Exception {
        mockMvc.perform(post("/api/decisions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {"title":" ","context":"A context that should not be accepted."}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }
}
