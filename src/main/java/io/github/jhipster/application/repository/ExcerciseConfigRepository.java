package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ExcerciseConfig;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExcerciseConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExcerciseConfigRepository extends JpaRepository<ExcerciseConfig, Long> {

}
