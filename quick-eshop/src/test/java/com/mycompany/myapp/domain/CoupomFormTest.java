package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoupomFormTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoupomForm.class);
        CoupomForm coupomForm1 = new CoupomForm();
        coupomForm1.setId(1L);
        CoupomForm coupomForm2 = new CoupomForm();
        coupomForm2.setId(coupomForm1.getId());
        assertThat(coupomForm1).isEqualTo(coupomForm2);
        coupomForm2.setId(2L);
        assertThat(coupomForm1).isNotEqualTo(coupomForm2);
        coupomForm1.setId(null);
        assertThat(coupomForm1).isNotEqualTo(coupomForm2);
    }
}
