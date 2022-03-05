package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoupomFormMapperTest {

    private CoupomFormMapper coupomFormMapper;

    @BeforeEach
    public void setUp() {
        coupomFormMapper = new CoupomFormMapperImpl();
    }
}
