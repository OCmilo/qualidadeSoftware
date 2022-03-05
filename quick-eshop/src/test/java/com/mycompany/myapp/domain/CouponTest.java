package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CouponTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coupon.class);
        Coupon coupon1 = new Coupon();
        coupon1.setId(1L);
        Coupon coupon2 = new Coupon();
        coupon2.setId(coupon1.getId());
        assertThat(coupon1).isEqualTo(coupon2);
        coupon2.setId(2L);
        assertThat(coupon1).isNotEqualTo(coupon2);
        coupon1.setId(null);
        assertThat(coupon1).isNotEqualTo(coupon2);
    }
}
