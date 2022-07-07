package com.gamingworld.app.gamingworld.profile.domain.persitence;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.StreamerSponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamerSponsorRepository extends JpaRepository<StreamerSponsor, Long> {
}
