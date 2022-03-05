package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Coupon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Coupon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {}
