package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CoupomForm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the CoupomForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoupomFormRepository extends JpaRepository<CoupomForm, Long> {}
