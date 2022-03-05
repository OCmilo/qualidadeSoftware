package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WarrantyFormDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WarrantyFormDTO.class);
        WarrantyFormDTO warrantyFormDTO1 = new WarrantyFormDTO();
        warrantyFormDTO1.setId(1L);
        WarrantyFormDTO warrantyFormDTO2 = new WarrantyFormDTO();
        assertThat(warrantyFormDTO1).isNotEqualTo(warrantyFormDTO2);
        warrantyFormDTO2.setId(warrantyFormDTO1.getId());
        assertThat(warrantyFormDTO1).isEqualTo(warrantyFormDTO2);
        warrantyFormDTO2.setId(2L);
        assertThat(warrantyFormDTO1).isNotEqualTo(warrantyFormDTO2);
        warrantyFormDTO1.setId(null);
        assertThat(warrantyFormDTO1).isNotEqualTo(warrantyFormDTO2);
    }
}
