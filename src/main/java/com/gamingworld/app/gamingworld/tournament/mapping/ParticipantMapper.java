package com.gamingworld.app.gamingworld.tournament.mapping;

import com.gamingworld.app.gamingworld.shared.mapping.EnhancedModelMapper;
import com.gamingworld.app.gamingworld.tournament.domain.model.entity.Participant;
import com.gamingworld.app.gamingworld.tournament.resource.CreateParticipantResource;
import com.gamingworld.app.gamingworld.tournament.resource.ParticipantResource;
import com.gamingworld.app.gamingworld.tournament.resource.UpdateTournamentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ParticipantMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ParticipantResource toResource(Participant model) { return mapper.map(model, ParticipantResource.class);
    }

    public Page<ParticipantResource> modelListToPage(List<Participant> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ParticipantResource.class), pageable, modelList.size());
    }

    public Participant toModel(CreateParticipantResource resource) {
        return mapper.map(resource, Participant.class);
    }

    public Participant toModel(UpdateTournamentResource resource) {
        return mapper.map(resource, Participant.class);
    }
}

