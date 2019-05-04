package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FinishedExcercise;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FinishedExcercise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinishedExcerciseRepository extends JpaRepository<FinishedExcercise, Long> {

}
