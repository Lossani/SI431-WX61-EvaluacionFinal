package com.gamingworld.app.gamingworld.tournaments;

import com.gamingworld.app.gamingworld.AbstractTest;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Tournament;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GetTournamentControllerTest extends AbstractTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllTournamentsList() throws Exception {
        String uri = "/api/v1/tournaments";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Tournament[] tournamentList = super.mapFromJSON(content, Tournament[].class);

        // Si existen torneos, nos aseguramos que los datos que devuelve sean válidos, por ejemplo, si los ID no son nulos.
        if (tournamentList.length > 0) {
            for (Tournament tournament : tournamentList) {
                assertNotNull(tournament.getId());
            }
        }
    }

    @Test
    public void getAllTournamentsByUser() throws Exception {
        String uri = "/api/v1/tournaments/user/1";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Tournament[] tournamentList = super.mapFromJSON(content, Tournament[].class);

        // Aseguramos que los torneos devueltos pertenezcan al usuario en prueba.
        if (tournamentList.length > 0) {
            for (Tournament tournament : tournamentList) {
                assertEquals(tournament.getUser().getId(), 1);
            }
        }
    }
}
