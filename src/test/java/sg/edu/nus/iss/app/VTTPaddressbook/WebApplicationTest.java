package sg.edu.nus.iss.app.VTTPaddressbook;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTest {
    private Logger logger = Logger.getLogger(WebApplicationTest.class.getName());

    @Autowired // for dependency injection, allows creation of Bean without new notation
    private MockMvc mockMVC;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void saveContactTest() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("name", "testName");
        input.put("email", "test@email.com");
        input.put("phone", "12345678");
        logger.log(Level.INFO, objectMapper.writeValueAsString(input));

        mockMVC.perform(post("/contact2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
