package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Warranty;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Warranty entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Long> {}
