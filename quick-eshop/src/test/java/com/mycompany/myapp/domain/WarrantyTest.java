package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WarrantyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Warranty.class);
        Warranty warranty1 = new Warranty();
        warranty1.setId(1L);
        Warranty warranty2 = new Warranty();
        warranty2.setId(warranty1.getId());
        assertThat(warranty1).isEqualTo(warranty2);
        warranty2.setId(2L);
        assertThat(warranty1).isNotEqualTo(warranty2);
        warranty1.setId(null);
        assertThat(warranty1).isNotEqualTo(warranty2);
    }
}
