package com.gamingworld.app.gamingworld.tournaments;

import com.gamingworld.app.gamingworld.AbstractTest;
import com.gamingworld.app.gamingworld.tournament.resource.CreateParticipantResource;
import com.gamingworld.app.gamingworld.tournament.resource.CreateTournamentResource;
import com.gamingworld.app.gamingworld.tournament.resource.ParticipantResource;
import com.gamingworld.app.gamingworld.tournament.resource.TournamentResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PostTournamentControllerTest extends AbstractTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userCreatesTournament() throws Exception {
        String uri = "/api/v1/tournaments/13/create";

        CreateTournamentResource tournament = new CreateTournamentResource();
        tournament.setTitle("Tournament Testing");
        tournament.setDescription("Esto es un torneo de prueba para probar la funcionalidad del API.");
        tournament.setUrlToImage("https://hipertextual.com/wp-content/uploads/2022/01/call-of-duty.jpeg");
        tournament.setPrizePool(100);
        tournament.setTournamentCapacity(50);

        String input = "2022-05-08 20:20";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        tournament.setTournamentDate(parser.parse(input));

        tournament.setGameId(621);
        tournament.setTournamentStatus(true);
        tournament.setIsTeamMode(false);

        String inputJson = super.mapToJSON(tournament);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TournamentResource createdTournament = super.mapFromJSON(content, TournamentResource.class);

        assertNotNull(createdTournament);
        assertNotNull(createdTournament.getId());
    }

    @Test
    public void userSignupsToTournament() throws Exception {
        String uri = "/api/v1/tournaments/13/participants";

        CreateParticipantResource participant = new CreateParticipantResource();
        participant.setTournamentId(13L);
        participant.setPoints(0);
        participant.setParticipantProfileId(1L);

        String inputJson = super.mapToJSON(participant);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ParticipantResource createdParticipant = super.mapFromJSON(content, ParticipantResource.class);

        assertNotNull(createdParticipant);
        assertNotNull(createdParticipant.getId());
    }
}