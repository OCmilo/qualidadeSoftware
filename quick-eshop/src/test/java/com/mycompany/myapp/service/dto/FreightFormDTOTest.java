package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FreightFormDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreightFormDTO.class);
        FreightFormDTO freightFormDTO1 = new FreightFormDTO();
        freightFormDTO1.setId(1L);
        FreightFormDTO freightFormDTO2 = new FreightFormDTO();
        assertThat(freightFormDTO1).isNotEqualTo(freightFormDTO2);
        freightFormDTO2.setId(freightFormDTO1.getId());
        assertThat(freightFormDTO1).isEqualTo(freightFormDTO2);
        freightFormDTO2.setId(2L);
        assertThat(freightFormDTO1).isNotEqualTo(freightFormDTO2);
        freightFormDTO1.setId(null);
        assertThat(freightFormDTO1).isNotEqualTo(freightFormDTO2);
    }
}
