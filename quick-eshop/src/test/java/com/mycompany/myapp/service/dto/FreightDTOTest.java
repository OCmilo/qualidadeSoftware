package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FreightDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreightDTO.class);
        FreightDTO freightDTO1 = new FreightDTO();
        freightDTO1.setId(1L);
        FreightDTO freightDTO2 = new FreightDTO();
        assertThat(freightDTO1).isNotEqualTo(freightDTO2);
        freightDTO2.setId(freightDTO1.getId());
        assertThat(freightDTO1).isEqualTo(freightDTO2);
        freightDTO2.setId(2L);
        assertThat(freightDTO1).isNotEqualTo(freightDTO2);
        freightDTO1.setId(null);
        assertThat(freightDTO1).isNotEqualTo(freightDTO2);
    }
}
