package com.gamingworld.app.gamingworld.tournament.api;

import com.gamingworld.app.gamingworld.profile.domain.persitence.ProfileRepository;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Participant;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Team;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.TeamParticipant;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Tournament;
import com.gamingworld.app.gamingworld.tournament.domain.service.ParticipantService;
import com.gamingworld.app.gamingworld.tournament.domain.service.TeamParticipantService;
import com.gamingworld.app.gamingworld.tournament.domain.service.TeamService;
import com.gamingworld.app.gamingworld.tournament.domain.service.TournamentService;
import com.gamingworld.app.gamingworld.tournament.mapping.ParticipantMapper;
import com.gamingworld.app.gamingworld.tournament.mapping.TeamMapper;
import com.gamingworld.app.gamingworld.tournament.mapping.TeamParticipantMapper;
import com.gamingworld.app.gamingworld.tournament.mapping.TournamentMapper;
import com.gamingworld.app.gamingworld.tournament.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/tournaments")
@Tag(name = "Tournament Controller", description = "Operations for tournament management.")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamParticipantService teamParticipantService;

    @Autowired
    private TournamentMapper tournamentMapper;

    @Autowired
    private ParticipantMapper participantMapper;

    @Autowired
    private TeamParticipantMapper teamParticipantMapper;

    @Autowired
    private TeamMapper teamMapper;

    @GetMapping
    @Operation(summary = "Retrieves a full list of existing tournaments.", description = "Retrieves a full list of existing tournaments.")
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAll();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Retrieves a full list of tournaments created by a user.", description = "Retrieves a full list of tournaments created by a user.")
    public List<Tournament> getTournamentsByUserId(@PathVariable("userId") Long userId) {
        return tournamentService.getTournamentsByUserId(userId);
    }

    @GetMapping("{tournamentId}")
    @Operation(summary = "Retrieves a tournament by its ID.", description = "Retrieves a tournament by its ID.")
    public Tournament getTournamentById(@PathVariable("tournamentId") Long tournamentId) {
        return tournamentService.getById(tournamentId);
    }

    @PostMapping("{userId}/create")
    @Operation(summary = "Creates a tournament with the user as administrator.", description = "Creates a tournament with the user as administrator.")
    public TournamentResource createTournament(@RequestBody CreateTournamentResource request, @PathVariable("userId") Long userId) {
        return tournamentMapper.toResource(tournamentService.create(userId,tournamentMapper.toModel(request)));
    }

    // Participants
    @PostMapping("{tournamentId}/participants")
    @Operation(summary = "Creates new participants to existing tournament.", description = "Creates new participants to existing tournament.")
    public ParticipantResource createParticipantByTournamentId(@RequestBody CreateParticipantResource request, @PathVariable("tournamentId") Long tournamentId) {

        return participantMapper.toResource(participantService.create(tournamentId, participantMapper.toModel(request)));
    }

    @GetMapping("{tournamentId}/participants/{participantId}/validate")
    @Operation(summary = "Validates if a participant is already in a specific tournament.", description = "Validates if a participant is already in a specific tournament.")
    public Boolean validateParticipantTournament(@PathVariable("tournamentId") Long tournamentId, @PathVariable("participantId") Long participantId){
        return participantService.validateParticipantInTournament(tournamentId, participantId);
    }


    @GetMapping("{tournamentId}/participants")
    @Operation(summary = "Retrieves a list of all participants in a tournament.", description = "Retrieves a list of all participants in a tournament.")
    public List<Participant> getAllParticipantsByTournamentId(@PathVariable("tournamentId") Long tournamentId) {
        return participantService.getAllByTournamentId(tournamentId);
    }

    @PutMapping("{tournamentId}/participants/{participantId}")
    @Operation(summary = "Updates a participant points.", description = "Updates a participant points.")
    public ParticipantResource updateParticipantPoints(@PathVariable("tournamentId") Long tournamentId, @PathVariable("participantId") Long participantId, @RequestParam int points){
        return participantMapper.toResource(tournamentService.updateParticipantPoints(tournamentId, participantId, points));
    }

    @DeleteMapping("{tournamentId}/participants/{userId}")
    @Operation(summary = "Deletes a participant from a tournament.", description = "Deletes a participant from a tournament.")
    public ResponseEntity<?> deleteParticipantInTournament(@PathVariable("tournamentId") Long tournamentId, @PathVariable("userId") Long userId){
        return tournamentService.deleteParticipantInTournament(tournamentId, userId);
    }

    // TEAMS

    @PostMapping("{tournamentId}/teams")
    @Operation(summary = "Creates a new team in a TEAM-BASED tournament.", description = "Creates a new team in a TEAM-BASED tournament.")
    public TeamResource createTeamByTournamentId(@RequestBody CreateTeamResource request, @PathVariable("tournamentId") Long tournamentId) {

        return teamMapper.toResource(teamService.create(tournamentId, teamMapper.toModel(request)));
    }

    @GetMapping("{tournamentId}/teams")
    @Operation(summary = "Retrieves a full list of teams in a tournament.", description = "Retrieves a full list of teams in a tournament.")
    public List<Team> getAllTeamsByTournamentId(@PathVariable("tournamentId") Long tournamentId) {
        return teamService.getAllByTournamentId(tournamentId);
    }

    @PutMapping("{tournamentId}/teams/{teamId}")
    @Operation(summary = "Updates points of a team in a tournament.", description = "Updates points of a team in a tournament.")
    public TeamResource updateTeamPoints(@PathVariable("tournamentId") Long tournamentId, @PathVariable("teamId") Long teamId, @RequestParam int points){
        return teamMapper.toResource(tournamentService.updateTeamPoints(tournamentId, teamId, points));
    }

    @DeleteMapping("{tournamentId}/teams/{teamId}")
    @Operation(summary = "Deletes a team from a tournament.", description = "Deletes a team from a tournament.")
    public ResponseEntity<?> deleteTeamInTournament(@PathVariable("teamId") Long teamId){
        return teamService.delete(teamId);
    }

    // Team Participants

    @PostMapping("{tournamentId}/teams/{teamId}/participants")
    @Operation(summary = "Creates a new participant in a team.", description = "Creates a new participant in a team.")
    public TeamParticipantResource createTeamParticipantByTeamId(@PathVariable("tournamentId") Long tournamentId, @RequestBody CreateTeamParticipantResource request, @PathVariable("teamId") Long teamId) {

        return teamParticipantMapper.toResource(teamParticipantService.create(tournamentId, teamId, teamParticipantMapper.toModel(request)));
    }

    @GetMapping("{tournamentId}/teams/{teamId}/participants")
    @Operation(summary = "Retrieves all members of a team.", description = "Retrieves all members of a team.")
    public List<TeamParticipant> getAllTeamParticipantsByTeamId(@PathVariable("teamId") Long teamId) {
        return teamParticipantService.getAllByTeamId(teamId);
    }

    @DeleteMapping("{tournamentId}/teams/{teamId}/participants/{teamParticipantId}")
    @Operation(summary = "Deletes a member of a team.", description = "Deletes a member of a team.")
    public ResponseEntity<?> deleteTeamParticipantInTeam(@PathVariable("teamParticipantId") Long teamParticipantId){
        return teamParticipantService.delete(teamParticipantId);
    }

    // -- Team Participants

    // -- TEAMS

    @PutMapping("{tournamentId}")
    @Operation(summary = "Updates a tournament data.", description = "Updates a tournament data.")
    public TournamentResource updateTournament(@PathVariable Long tournamentId, @RequestBody UpdateTournamentResource request) {
        return tournamentMapper.toResource(tournamentService.update(tournamentId, tournamentMapper.toModel(request)));
    }

    @DeleteMapping("{tournamentId}")
    @Operation(summary = "Removes a tournament.", description = "Removes a tournament.")
    public ResponseEntity<?> deleteTournament(@PathVariable Long tournamentId) {
        return tournamentService.delete(tournamentId);
    }

    @PutMapping("{tournamentId}/end")
    @Operation(summary = "Marks a tournament as completed status.", description = "Marks a tournament as completed status.")
    public TournamentResource endTournament(@PathVariable("tournamentId") Long tournamentId){
        return tournamentMapper.toResource(tournamentService.endTournament(tournamentId));
    }

}
