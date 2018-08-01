package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.OpeningHour;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OpeningHour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpeningHourRepository extends JpaRepository<OpeningHour, Long> {

}
