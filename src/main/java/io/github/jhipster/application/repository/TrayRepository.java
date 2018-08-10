package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Tray;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Tray entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TrayRepository extends JpaRepository<Tray, Long> {

    @Query("select tray from Tray tray where tray.user.login = ?#{principal.username}")
    List<Tray> findByUserIsCurrentUser();

    @Query("select tray from Tray tray where tray.user.id=:userId  and tray.status='opened'")
    Optional<Tray> findByUserId(@Param("userId") Long userId);

}
