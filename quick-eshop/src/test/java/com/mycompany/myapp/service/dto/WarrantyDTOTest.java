package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WarrantyDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(WarrantyDTO.class);
        WarrantyDTO warrantyDTO1 = new WarrantyDTO();
        warrantyDTO1.setId(1L);
        WarrantyDTO warrantyDTO2 = new WarrantyDTO();
        assertThat(warrantyDTO1).isNotEqualTo(warrantyDTO2);
        warrantyDTO2.setId(warrantyDTO1.getId());
        assertThat(warrantyDTO1).isEqualTo(warrantyDTO2);
        warrantyDTO2.setId(2L);
        assertThat(warrantyDTO1).isNotEqualTo(warrantyDTO2);
        warrantyDTO1.setId(null);
        assertThat(warrantyDTO1).isNotEqualTo(warrantyDTO2);
    }
}
