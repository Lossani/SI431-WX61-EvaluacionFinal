package com.gamingworld.app.gamingworld.tournament.mapping;

import com.gamingworld.app.gamingworld.shared.mapping.EnhancedModelMapper;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Tournament;
import com.gamingworld.app.gamingworld.tournament.resource.CreateTournamentResource;
import com.gamingworld.app.gamingworld.tournament.resource.TournamentResource;
import com.gamingworld.app.gamingworld.tournament.resource.UpdateTournamentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TournamentMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TournamentResource toResource(Tournament model) { return mapper.map(model, TournamentResource.class);
    }

    public Page<TournamentResource> modelListToPage(List<Tournament> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TournamentResource.class), pageable, modelList.size());
    }

    public Tournament toModel(CreateTournamentResource resource) {
        return mapper.map(resource, Tournament.class);
    }

    public Tournament toModel(UpdateTournamentResource resource) {
        return mapper.map(resource, Tournament.class);
    }
}

