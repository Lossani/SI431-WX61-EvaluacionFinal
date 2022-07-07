package com.gamingworld.app.gamingworld.tournament.mapping;

import com.gamingworld.app.gamingworld.shared.mapping.EnhancedModelMapper;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.TeamParticipant;
import com.gamingworld.app.gamingworld.tournament.resource.CreateTeamParticipantResource;
import com.gamingworld.app.gamingworld.tournament.resource.TeamParticipantResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TeamParticipantMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TeamParticipantResource toResource(TeamParticipant model) { return mapper.map(model, TeamParticipantResource.class);
    }

    public Page<TeamParticipantResource> modelListToPage(List<TeamParticipant> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TeamParticipantResource.class), pageable, modelList.size());
    }

    public TeamParticipant toModel(CreateTeamParticipantResource resource) {
        mapper.getConfiguration().setAmbiguityIgnored(true);
        return mapper.map(resource, TeamParticipant.class);
    }
}

