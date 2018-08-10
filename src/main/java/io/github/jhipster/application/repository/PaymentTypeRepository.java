package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PaymentType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PaymentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

}
