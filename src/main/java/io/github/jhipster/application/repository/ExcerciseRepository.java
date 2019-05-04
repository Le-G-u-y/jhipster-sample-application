package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Excercise;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Excercise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExcerciseRepository extends JpaRepository<Excercise, Long> {

}
