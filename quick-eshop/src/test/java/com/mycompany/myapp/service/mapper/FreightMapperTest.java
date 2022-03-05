package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FreightMapperTest {

    private FreightMapper freightMapper;

    @BeforeEach
    public void setUp() {
        freightMapper = new FreightMapperImpl();
    }
}
