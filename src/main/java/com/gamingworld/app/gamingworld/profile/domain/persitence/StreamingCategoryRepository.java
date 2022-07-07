package com.gamingworld.app.gamingworld.profile.domain.persitence;

import com.gamingworld.app.gamingworld.profile.domain.model.entity.StreamingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingCategoryRepository extends JpaRepository<StreamingCategory, Long> {
}
