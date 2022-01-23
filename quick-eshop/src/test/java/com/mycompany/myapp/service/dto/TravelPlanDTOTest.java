package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TravelPlanDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TravelPlanDTO.class);
        TravelPlanDTO travelPlanDTO1 = new TravelPlanDTO();
        travelPlanDTO1.setId(1L);
        TravelPlanDTO travelPlanDTO2 = new TravelPlanDTO();
        assertThat(travelPlanDTO1).isNotEqualTo(travelPlanDTO2);
        travelPlanDTO2.setId(travelPlanDTO1.getId());
        assertThat(travelPlanDTO1).isEqualTo(travelPlanDTO2);
        travelPlanDTO2.setId(2L);
        assertThat(travelPlanDTO1).isNotEqualTo(travelPlanDTO2);
        travelPlanDTO1.setId(null);
        assertThat(travelPlanDTO1).isNotEqualTo(travelPlanDTO2);
    }
}
