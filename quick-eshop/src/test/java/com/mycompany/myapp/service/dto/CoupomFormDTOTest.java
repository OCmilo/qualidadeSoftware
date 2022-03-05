package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoupomFormDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoupomFormDTO.class);
        CoupomFormDTO coupomFormDTO1 = new CoupomFormDTO();
        coupomFormDTO1.setId(1L);
        CoupomFormDTO coupomFormDTO2 = new CoupomFormDTO();
        assertThat(coupomFormDTO1).isNotEqualTo(coupomFormDTO2);
        coupomFormDTO2.setId(coupomFormDTO1.getId());
        assertThat(coupomFormDTO1).isEqualTo(coupomFormDTO2);
        coupomFormDTO2.setId(2L);
        assertThat(coupomFormDTO1).isNotEqualTo(coupomFormDTO2);
        coupomFormDTO1.setId(null);
        assertThat(coupomFormDTO1).isNotEqualTo(coupomFormDTO2);
    }
}
