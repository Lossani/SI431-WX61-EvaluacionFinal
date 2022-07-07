package com.gamingworld.app.gamingworld.profiles;

import com.gamingworld.app.gamingworld.AbstractTest;
import com.gamingworld.app.gamingworld.profile.domain.model.entity.Profile;
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
public class GetProfileControllerTest extends AbstractTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllProfilesList() throws Exception {
        String uri = "/api/v1/profiles";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Profile[] profileList = super.mapFromJSON(content, Profile[].class);

        // Si existen perfiles, nos aseguramos que los datos que devuelve sean válidos, por ejemplo, si los ID no son nulos.
        if (profileList.length > 0) {
            for (Profile profile : profileList) {
                assertNotNull(profile.getId());
            }
        }
    }
}
