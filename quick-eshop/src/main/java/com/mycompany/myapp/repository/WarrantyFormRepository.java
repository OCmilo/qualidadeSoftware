package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.WarrantyForm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the WarrantyForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WarrantyFormRepository extends JpaRepository<WarrantyForm, Long> {}
