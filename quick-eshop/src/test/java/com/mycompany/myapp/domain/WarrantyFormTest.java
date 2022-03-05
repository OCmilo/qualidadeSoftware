package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WarrantyFormTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WarrantyForm.class);
        WarrantyForm warrantyForm1 = new WarrantyForm();
        warrantyForm1.setId(1L);
        WarrantyForm warrantyForm2 = new WarrantyForm();
        warrantyForm2.setId(warrantyForm1.getId());
        assertThat(warrantyForm1).isEqualTo(warrantyForm2);
        warrantyForm2.setId(2L);
        assertThat(warrantyForm1).isNotEqualTo(warrantyForm2);
        warrantyForm1.setId(null);
        assertThat(warrantyForm1).isNotEqualTo(warrantyForm2);
    }
}
