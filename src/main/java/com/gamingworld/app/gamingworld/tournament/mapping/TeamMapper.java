package com.gamingworld.app.gamingworld.tournament.mapping;

import com.gamingworld.app.gamingworld.shared.mapping.EnhancedModelMapper;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Team;
import com.gamingworld.app.gamingworld.tournament.resource.CreateTeamResource;
import com.gamingworld.app.gamingworld.tournament.resource.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TeamMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TeamResource toResource(Team model) { return mapper.map(model, TeamResource.class);
    }

    public Page<TeamResource> modelListToPage(List<Team> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TeamResource.class), pageable, modelList.size());
    }

    public Team toModel(CreateTeamResource resource) {
        return mapper.map(resource, Team.class);
    }

}

