package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TravelPlan;
import com.mycompany.myapp.repository.TravelPlanRepository;
import com.mycompany.myapp.service.dto.TravelPlanDTO;
import com.mycompany.myapp.service.mapper.TravelPlanMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TravelPlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TravelPlanResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AIRLINE_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AIRLINE_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AIRLINE_TICKET_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_AIRLINE_TICKET_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_HOTEL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HOTEL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOTEL_BOOKING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_HOTEL_BOOKING_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CAR_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAR_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAR_BOOKING_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CAR_BOOKING_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/travel-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TravelPlanRepository travelPlanRepository;

    @Autowired
    private TravelPlanMapper travelPlanMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTravelPlanMockMvc;

    private TravelPlan travelPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelPlan createEntity(EntityManager em) {
        TravelPlan travelPlan = new TravelPlan()
            .name(DEFAULT_NAME)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .airlineCompanyName(DEFAULT_AIRLINE_COMPANY_NAME)
            .airlineTicketNumber(DEFAULT_AIRLINE_TICKET_NUMBER)
            .hotelName(DEFAULT_HOTEL_NAME)
            .hotelBookingNumber(DEFAULT_HOTEL_BOOKING_NUMBER)
            .carCompanyName(DEFAULT_CAR_COMPANY_NAME)
            .carBookingNumber(DEFAULT_CAR_BOOKING_NUMBER);
        return travelPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TravelPlan createUpdatedEntity(EntityManager em) {
        TravelPlan travelPlan = new TravelPlan()
            .name(UPDATED_NAME)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .airlineCompanyName(UPDATED_AIRLINE_COMPANY_NAME)
            .airlineTicketNumber(UPDATED_AIRLINE_TICKET_NUMBER)
            .hotelName(UPDATED_HOTEL_NAME)
            .hotelBookingNumber(UPDATED_HOTEL_BOOKING_NUMBER)
            .carCompanyName(UPDATED_CAR_COMPANY_NAME)
            .carBookingNumber(UPDATED_CAR_BOOKING_NUMBER);
        return travelPlan;
    }

    @BeforeEach
    public void initTest() {
        travelPlan = createEntity(em);
    }

    @Test
    @Transactional
    void getAllTravelPlans() throws Exception {
        // Initialize the database
        travelPlanRepository.saveAndFlush(travelPlan);

        // Get all the travelPlanList
        restTravelPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(travelPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].airlineCompanyName").value(hasItem(DEFAULT_AIRLINE_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].airlineTicketNumber").value(hasItem(DEFAULT_AIRLINE_TICKET_NUMBER)))
            .andExpect(jsonPath("$.[*].hotelName").value(hasItem(DEFAULT_HOTEL_NAME)))
            .andExpect(jsonPath("$.[*].hotelBookingNumber").value(hasItem(DEFAULT_HOTEL_BOOKING_NUMBER)))
            .andExpect(jsonPath("$.[*].carCompanyName").value(hasItem(DEFAULT_CAR_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].carBookingNumber").value(hasItem(DEFAULT_CAR_BOOKING_NUMBER)));
    }

    @Test
    @Transactional
    void getTravelPlan() throws Exception {
        // Initialize the database
        travelPlanRepository.saveAndFlush(travelPlan);

        // Get the travelPlan
        restTravelPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, travelPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(travelPlan.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.airlineCompanyName").value(DEFAULT_AIRLINE_COMPANY_NAME))
            .andExpect(jsonPath("$.airlineTicketNumber").value(DEFAULT_AIRLINE_TICKET_NUMBER))
            .andExpect(jsonPath("$.hotelName").value(DEFAULT_HOTEL_NAME))
            .andExpect(jsonPath("$.hotelBookingNumber").value(DEFAULT_HOTEL_BOOKING_NUMBER))
            .andExpect(jsonPath("$.carCompanyName").value(DEFAULT_CAR_COMPANY_NAME))
            .andExpect(jsonPath("$.carBookingNumber").value(DEFAULT_CAR_BOOKING_NUMBER));
    }

    @Test
    @Transactional
    void getNonExistingTravelPlan() throws Exception {
        // Get the travelPlan
        restTravelPlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
