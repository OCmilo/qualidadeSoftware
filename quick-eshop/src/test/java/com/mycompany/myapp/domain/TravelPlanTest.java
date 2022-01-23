package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TravelPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TravelPlan.class);
        TravelPlan travelPlan1 = new TravelPlan();
        travelPlan1.setId(1L);
        TravelPlan travelPlan2 = new TravelPlan();
        travelPlan2.setId(travelPlan1.getId());
        assertThat(travelPlan1).isEqualTo(travelPlan2);
        travelPlan2.setId(2L);
        assertThat(travelPlan1).isNotEqualTo(travelPlan2);
        travelPlan1.setId(null);
        assertThat(travelPlan1).isNotEqualTo(travelPlan2);
    }
}
