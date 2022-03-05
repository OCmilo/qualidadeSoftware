package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarrantyFormMapperTest {

    private WarrantyFormMapper warrantyFormMapper;

    @BeforeEach
    public void setUp() {
        warrantyFormMapper = new WarrantyFormMapperImpl();
    }
}
