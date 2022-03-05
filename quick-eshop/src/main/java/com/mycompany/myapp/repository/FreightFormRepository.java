package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.FreightForm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the FreightForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreightFormRepository extends JpaRepository<FreightForm, Long> {}
