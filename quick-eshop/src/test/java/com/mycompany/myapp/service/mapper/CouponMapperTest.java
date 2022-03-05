package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CouponMapperTest {

    private CouponMapper couponMapper;

    @BeforeEach
    public void setUp() {
        couponMapper = new CouponMapperImpl();
    }
}
