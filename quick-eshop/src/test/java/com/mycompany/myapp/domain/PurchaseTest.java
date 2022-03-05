package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Purchase.class);
        Purchase purchase1 = new Purchase();
        purchase1.setId(1L);
        Purchase purchase2 = new Purchase();
        purchase2.setId(purchase1.getId());
        assertThat(purchase1).isEqualTo(purchase2);
        purchase2.setId(2L);
        assertThat(purchase1).isNotEqualTo(purchase2);
        purchase1.setId(null);
        assertThat(purchase1).isNotEqualTo(purchase2);
    }
}
