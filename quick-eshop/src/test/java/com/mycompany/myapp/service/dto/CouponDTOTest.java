package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CouponDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CouponDTO.class);
        CouponDTO couponDTO1 = new CouponDTO();
        couponDTO1.setId(1L);
        CouponDTO couponDTO2 = new CouponDTO();
        assertThat(couponDTO1).isNotEqualTo(couponDTO2);
        couponDTO2.setId(couponDTO1.getId());
        assertThat(couponDTO1).isEqualTo(couponDTO2);
        couponDTO2.setId(2L);
        assertThat(couponDTO1).isNotEqualTo(couponDTO2);
        couponDTO1.setId(null);
        assertThat(couponDTO1).isNotEqualTo(couponDTO2);
    }
}
