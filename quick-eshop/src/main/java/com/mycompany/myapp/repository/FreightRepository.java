package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Freight;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Freight entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FreightRepository extends JpaRepository<Freight, Long> {}
