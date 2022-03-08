package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Purchase;
import com.mycompany.myapp.repository.PurchaseRepository;
import com.mycompany.myapp.service.dto.PurchaseDTO;
import com.mycompany.myapp.service.mapper.PurchaseMapper;
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
 * Integration tests for the {@link PurchaseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PurchaseResourceIT {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_USER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_USER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    private static final Boolean DEFAULT_CONFIRMATION = false;
    private static final Boolean UPDATED_CONFIRMATION = true;

    private static final Boolean DEFAULT_WITH_WARRANTY = false;
    private static final Boolean UPDATED_WITH_WARRANTY = true;

    private static final Boolean DEFAULT_WITH_COUPON = false;
    private static final Boolean UPDATED_WITH_COUPON = true;

    private static final Boolean DEFAULT_ADD_PRODUCTS = false;
    private static final Boolean UPDATED_ADD_PRODUCTS = true;

    private static final String ENTITY_API_URL = "/api/purchases";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPurchaseMockMvc;

    private Purchase purchase;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Purchase createEntity(EntityManager em) {
        Purchase purchase = new Purchase()
            .userName(DEFAULT_USER_NAME)
            .userEmail(DEFAULT_USER_EMAIL)
            .address(DEFAULT_ADDRESS)
            .quantity(DEFAULT_QUANTITY)
            .confirmation(DEFAULT_CONFIRMATION)
            .withWarranty(DEFAULT_WITH_WARRANTY)
            .withCoupon(DEFAULT_WITH_COUPON)
            .addProducts(DEFAULT_ADD_PRODUCTS);
        return purchase;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Purchase createUpdatedEntity(EntityManager em) {
        Purchase purchase = new Purchase()
            .userName(UPDATED_USER_NAME)
            .userEmail(UPDATED_USER_EMAIL)
            .address(UPDATED_ADDRESS)
            .quantity(UPDATED_QUANTITY)
            .confirmation(UPDATED_CONFIRMATION)
            .withWarranty(UPDATED_WITH_WARRANTY)
            .withCoupon(UPDATED_WITH_COUPON)
            .addProducts(UPDATED_ADD_PRODUCTS);
        return purchase;
    }

    @BeforeEach
    public void initTest() {
        purchase = createEntity(em);
    }

    @Test
    @Transactional
    void getAllPurchases() throws Exception {
        // Initialize the database
        purchaseRepository.saveAndFlush(purchase);

        // Get all the purchaseList
        restPurchaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(purchase.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].userEmail").value(hasItem(DEFAULT_USER_EMAIL)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].confirmation").value(hasItem(DEFAULT_CONFIRMATION.booleanValue())))
            .andExpect(jsonPath("$.[*].withWarranty").value(hasItem(DEFAULT_WITH_WARRANTY.booleanValue())))
            .andExpect(jsonPath("$.[*].withCoupon").value(hasItem(DEFAULT_WITH_COUPON.booleanValue())))
            .andExpect(jsonPath("$.[*].addProducts").value(hasItem(DEFAULT_ADD_PRODUCTS.booleanValue())));
    }

    @Test
    @Transactional
    void getPurchase() throws Exception {
        // Initialize the database
        purchaseRepository.saveAndFlush(purchase);

        // Get the purchase
        restPurchaseMockMvc
            .perform(get(ENTITY_API_URL_ID, purchase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(purchase.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.userEmail").value(DEFAULT_USER_EMAIL))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.confirmation").value(DEFAULT_CONFIRMATION.booleanValue()))
            .andExpect(jsonPath("$.withWarranty").value(DEFAULT_WITH_WARRANTY.booleanValue()))
            .andExpect(jsonPath("$.withCoupon").value(DEFAULT_WITH_COUPON.booleanValue()))
            .andExpect(jsonPath("$.addProducts").value(DEFAULT_ADD_PRODUCTS.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingPurchase() throws Exception {
        // Get the purchase
        restPurchaseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
