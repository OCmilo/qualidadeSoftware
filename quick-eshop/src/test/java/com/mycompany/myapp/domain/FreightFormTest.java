package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FreightFormTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreightForm.class);
        FreightForm freightForm1 = new FreightForm();
        freightForm1.setId(1L);
        FreightForm freightForm2 = new FreightForm();
        freightForm2.setId(freightForm1.getId());
        assertThat(freightForm1).isEqualTo(freightForm2);
        freightForm2.setId(2L);
        assertThat(freightForm1).isNotEqualTo(freightForm2);
        freightForm1.setId(null);
        assertThat(freightForm1).isNotEqualTo(freightForm2);
    }
}
