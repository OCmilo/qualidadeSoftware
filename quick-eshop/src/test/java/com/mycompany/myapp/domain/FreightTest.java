package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FreightTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Freight.class);
        Freight freight1 = new Freight();
        freight1.setId(1L);
        Freight freight2 = new Freight();
        freight2.setId(freight1.getId());
        assertThat(freight1).isEqualTo(freight2);
        freight2.setId(2L);
        assertThat(freight1).isNotEqualTo(freight2);
        freight1.setId(null);
        assertThat(freight1).isNotEqualTo(freight2);
    }
}
