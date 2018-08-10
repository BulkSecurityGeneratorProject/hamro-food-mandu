package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Tray;
import io.github.jhipster.application.domain.TrayFood;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the TrayFood entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrayFoodRepository extends JpaRepository<TrayFood, Long> {
    @Query("select t from TrayFood t where t.tray.id=:trayId ")
    Optional<List<TrayFood>> findByTrayId(@Param("trayId") Long trayId);
}
