package dev.feldmann.bootcamp.wave2.diplomas.integration;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class DiplomaController {


  @Autowired
  private MockMvc mvc;


  @Test
  void shouldReturnAverageWithValidInput() throws Exception {

    HttpMethod method = HttpMethod.POST;
    String route = "/analyzeNotes";
    String content = "{"
        + "  \"name\": \"EdnaldoPereira\","
        + "  \"subjects\": ["
        + "    {"
        + "      \"name\": \"TrucoIndustrial\","
        + "      \"note\": 9"
        + "    }"
        + "  ]"
        + "}";

    String expectedResponse = "{\n"
        + "  \"student\": \"EdnaldoPereira\",\n"
        + "  \"average\": 9.0,\n"
        + "  \"message\": \"Parabéns! Aprovado acima da média!\",\n"
        + "  \"subjects\": [\n"
        + "    {\n"
        + "      \"name\": \"TrucoIndustrial\",\n"
        + "      \"note\": 9\n"
        + "    }\n"
        + "  ]\n"
        + "}";

    mvc.perform(request(method, route)
        .contentType(MediaType.APPLICATION_JSON)
        .content(content))
       .andExpect(status().isOk())
       .andExpect(MockMvcResultMatchers.content().json(expectedResponse));
  }
}
