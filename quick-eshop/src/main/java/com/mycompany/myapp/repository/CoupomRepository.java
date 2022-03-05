package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Coupom;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Coupom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoupomRepository extends JpaRepository<Coupom, Long> {}
