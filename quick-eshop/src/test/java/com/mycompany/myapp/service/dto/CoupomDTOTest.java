package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoupomDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CoupomDTO.class);
        CoupomDTO coupomDTO1 = new CoupomDTO();
        coupomDTO1.setId(1L);
        CoupomDTO coupomDTO2 = new CoupomDTO();
        assertThat(coupomDTO1).isNotEqualTo(coupomDTO2);
        coupomDTO2.setId(coupomDTO1.getId());
        assertThat(coupomDTO1).isEqualTo(coupomDTO2);
        coupomDTO2.setId(2L);
        assertThat(coupomDTO1).isNotEqualTo(coupomDTO2);
        coupomDTO1.setId(null);
        assertThat(coupomDTO1).isNotEqualTo(coupomDTO2);
    }
}
