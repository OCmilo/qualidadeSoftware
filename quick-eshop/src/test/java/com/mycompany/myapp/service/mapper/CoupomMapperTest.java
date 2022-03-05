package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoupomMapperTest {

    private CoupomMapper coupomMapper;

    @BeforeEach
    public void setUp() {
        coupomMapper = new CoupomMapperImpl();
    }
}
