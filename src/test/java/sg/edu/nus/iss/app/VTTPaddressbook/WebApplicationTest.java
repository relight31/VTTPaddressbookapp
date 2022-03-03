package sg.edu.nus.iss.app.VTTPaddressbook;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebApplicationTest {
    private Logger logger = Logger.getLogger(WebApplicationTest.class.getName());

    @Autowired
    private MockMvc mockMVC;
    
}
