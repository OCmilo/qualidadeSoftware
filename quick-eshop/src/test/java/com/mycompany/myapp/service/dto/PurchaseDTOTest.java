package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseDTO.class);
        PurchaseDTO purchaseDTO1 = new PurchaseDTO();
        purchaseDTO1.setId(1L);
        PurchaseDTO purchaseDTO2 = new PurchaseDTO();
        assertThat(purchaseDTO1).isNotEqualTo(purchaseDTO2);
        purchaseDTO2.setId(purchaseDTO1.getId());
        assertThat(purchaseDTO1).isEqualTo(purchaseDTO2);
        purchaseDTO2.setId(2L);
        assertThat(purchaseDTO1).isNotEqualTo(purchaseDTO2);
        purchaseDTO1.setId(null);
        assertThat(purchaseDTO1).isNotEqualTo(purchaseDTO2);
    }
}
