package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FreightFormMapperTest {

    private FreightFormMapper freightFormMapper;

    @BeforeEach
    public void setUp() {
        freightFormMapper = new FreightFormMapperImpl();
    }
}
