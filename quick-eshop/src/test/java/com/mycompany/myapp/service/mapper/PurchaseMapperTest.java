package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseMapperTest {

    private PurchaseMapper purchaseMapper;

    @BeforeEach
    public void setUp() {
        purchaseMapper = new PurchaseMapperImpl();
    }
}
