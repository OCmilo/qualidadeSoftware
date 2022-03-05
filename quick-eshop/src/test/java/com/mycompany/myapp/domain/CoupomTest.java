package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoupomTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Coupom.class);
        Coupom coupom1 = new Coupom();
        coupom1.setId(1L);
        Coupom coupom2 = new Coupom();
        coupom2.setId(coupom1.getId());
        assertThat(coupom1).isEqualTo(coupom2);
        coupom2.setId(2L);
        assertThat(coupom1).isNotEqualTo(coupom2);
        coupom1.setId(null);
        assertThat(coupom1).isNotEqualTo(coupom2);
    }
}
