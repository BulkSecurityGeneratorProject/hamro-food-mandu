package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.HFMOrder;
import io.github.jhipster.application.domain.TrayFood;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the HFMOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HFMOrderRepository extends JpaRepository<HFMOrder, Long> {

    @Query("select hfm_order from HFMOrder hfm_order where hfm_order.user.login = ?#{principal.username}")
    List<HFMOrder> findByUserIsCurrentUser();

    @Query("select hfm_order from HFMOrder hfm_order where hfm_order.user.id=:userId")
    Optional<List<HFMOrder>> findByUserId(@Param("userId") Long userId);

}
