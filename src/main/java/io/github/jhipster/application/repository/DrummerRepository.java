package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Drummer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Drummer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DrummerRepository extends JpaRepository<Drummer, Long> {

}
