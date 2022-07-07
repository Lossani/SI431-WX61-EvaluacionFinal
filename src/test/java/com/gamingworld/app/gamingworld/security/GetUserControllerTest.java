package com.gamingworld.app.gamingworld.security;

import com.gamingworld.app.gamingworld.AbstractTest;
import com.gamingworld.app.gamingworld.security.domain.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GetUserControllerTest extends AbstractTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsersList() throws Exception {
        String uri = "/api/v1/users";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] userList = super.mapFromJSON(content, User[].class);

        // Si existen usuarios, nos aseguramos que los datos que devuelve sean válidos, por ejemplo, si los email no son nulos.
        if (userList.length > 0) {
            for (User user : userList) {
                assertNotNull(user.getEmail());
            }
        }
    }
}
