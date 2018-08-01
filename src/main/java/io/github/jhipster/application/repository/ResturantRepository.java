package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Resturant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Resturant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResturantRepository extends JpaRepository<Resturant, Long> {

    @Query(value = "select distinct resturant from Resturant resturant left join fetch resturant.openingHours",
        countQuery = "select count(distinct resturant) from Resturant resturant")
    Page<Resturant> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct resturant from Resturant resturant left join fetch resturant.openingHours")
    List<Resturant> findAllWithEagerRelationships();

    @Query("select resturant from Resturant resturant left join fetch resturant.openingHours where resturant.id =:id")
    Optional<Resturant> findOneWithEagerRelationships(@Param("id") Long id);

}
