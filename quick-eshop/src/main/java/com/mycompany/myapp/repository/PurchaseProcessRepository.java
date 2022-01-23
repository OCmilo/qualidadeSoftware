package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.PurchaseProcess;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PurchaseProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseProcessRepository extends JpaRepository<PurchaseProcess, Long> {
    Optional<PurchaseProcess> findByProcessInstanceId(Long processInstanceId);
}
