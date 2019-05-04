package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.FinishedSession;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FinishedSession entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinishedSessionRepository extends JpaRepository<FinishedSession, Long> {

}
