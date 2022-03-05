package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Freight;
import com.mycompany.myapp.repository.FreightRepository;
import com.mycompany.myapp.service.dto.FreightDTO;
import com.mycompany.myapp.service.mapper.FreightMapper;
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
 * Integration tests for the {@link FreightResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FreightResourceIT {

    private static final String DEFAULT_FREIGHTER = "AAAAAAAAAA";
    private static final String UPDATED_FREIGHTER = "BBBBBBBBBB";

    private static final Double DEFAULT_FREIGHT_PRICE = 1D;
    private static final Double UPDATED_FREIGHT_PRICE = 2D;

    private static final String ENTITY_API_URL = "/api/freights";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FreightRepository freightRepository;

    @Autowired
    private FreightMapper freightMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFreightMockMvc;

    private Freight freight;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Freight createEntity(EntityManager em) {
        Freight freight = new Freight().freighter(DEFAULT_FREIGHTER).freightPrice(DEFAULT_FREIGHT_PRICE);
        return freight;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Freight createUpdatedEntity(EntityManager em) {
        Freight freight = new Freight().freighter(UPDATED_FREIGHTER).freightPrice(UPDATED_FREIGHT_PRICE);
        return freight;
    }

    @BeforeEach
    public void initTest() {
        freight = createEntity(em);
    }

    @Test
    @Transactional
    void createFreight() throws Exception {
        int databaseSizeBeforeCreate = freightRepository.findAll().size();
        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);
        restFreightMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(freightDTO)))
            .andExpect(status().isCreated());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeCreate + 1);
        Freight testFreight = freightList.get(freightList.size() - 1);
        assertThat(testFreight.getFreighter()).isEqualTo(DEFAULT_FREIGHTER);
        assertThat(testFreight.getFreightPrice()).isEqualTo(DEFAULT_FREIGHT_PRICE);
    }

    @Test
    @Transactional
    void createFreightWithExistingId() throws Exception {
        // Create the Freight with an existing ID
        freight.setId(1L);
        FreightDTO freightDTO = freightMapper.toDto(freight);

        int databaseSizeBeforeCreate = freightRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreightMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(freightDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFreights() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        // Get all the freightList
        restFreightMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freight.getId().intValue())))
            .andExpect(jsonPath("$.[*].freighter").value(hasItem(DEFAULT_FREIGHTER)))
            .andExpect(jsonPath("$.[*].freightPrice").value(hasItem(DEFAULT_FREIGHT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    void getFreight() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        // Get the freight
        restFreightMockMvc
            .perform(get(ENTITY_API_URL_ID, freight.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(freight.getId().intValue()))
            .andExpect(jsonPath("$.freighter").value(DEFAULT_FREIGHTER))
            .andExpect(jsonPath("$.freightPrice").value(DEFAULT_FREIGHT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingFreight() throws Exception {
        // Get the freight
        restFreightMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFreight() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        int databaseSizeBeforeUpdate = freightRepository.findAll().size();

        // Update the freight
        Freight updatedFreight = freightRepository.findById(freight.getId()).get();
        // Disconnect from session so that the updates on updatedFreight are not directly saved in db
        em.detach(updatedFreight);
        updatedFreight.freighter(UPDATED_FREIGHTER).freightPrice(UPDATED_FREIGHT_PRICE);
        FreightDTO freightDTO = freightMapper.toDto(updatedFreight);

        restFreightMockMvc
            .perform(
                put(ENTITY_API_URL_ID, freightDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(freightDTO))
            )
            .andExpect(status().isOk());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
        Freight testFreight = freightList.get(freightList.size() - 1);
        assertThat(testFreight.getFreighter()).isEqualTo(UPDATED_FREIGHTER);
        assertThat(testFreight.getFreightPrice()).isEqualTo(UPDATED_FREIGHT_PRICE);
    }

    @Test
    @Transactional
    void putNonExistingFreight() throws Exception {
        int databaseSizeBeforeUpdate = freightRepository.findAll().size();
        freight.setId(count.incrementAndGet());

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreightMockMvc
            .perform(
                put(ENTITY_API_URL_ID, freightDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(freightDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFreight() throws Exception {
        int databaseSizeBeforeUpdate = freightRepository.findAll().size();
        freight.setId(count.incrementAndGet());

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(freightDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFreight() throws Exception {
        int databaseSizeBeforeUpdate = freightRepository.findAll().size();
        freight.setId(count.incrementAndGet());

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(freightDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFreightWithPatch() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        int databaseSizeBeforeUpdate = freightRepository.findAll().size();

        // Update the freight using partial update
        Freight partialUpdatedFreight = new Freight();
        partialUpdatedFreight.setId(freight.getId());

        restFreightMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFreight.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFreight))
            )
            .andExpect(status().isOk());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
        Freight testFreight = freightList.get(freightList.size() - 1);
        assertThat(testFreight.getFreighter()).isEqualTo(DEFAULT_FREIGHTER);
        assertThat(testFreight.getFreightPrice()).isEqualTo(DEFAULT_FREIGHT_PRICE);
    }

    @Test
    @Transactional
    void fullUpdateFreightWithPatch() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        int databaseSizeBeforeUpdate = freightRepository.findAll().size();

        // Update the freight using partial update
        Freight partialUpdatedFreight = new Freight();
        partialUpdatedFreight.setId(freight.getId());

        partialUpdatedFreight.freighter(UPDATED_FREIGHTER).freightPrice(UPDATED_FREIGHT_PRICE);

        restFreightMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFreight.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFreight))
            )
            .andExpect(status().isOk());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
        Freight testFreight = freightList.get(freightList.size() - 1);
        assertThat(testFreight.getFreighter()).isEqualTo(UPDATED_FREIGHTER);
        assertThat(testFreight.getFreightPrice()).isEqualTo(UPDATED_FREIGHT_PRICE);
    }

    @Test
    @Transactional
    void patchNonExistingFreight() throws Exception {
        int databaseSizeBeforeUpdate = freightRepository.findAll().size();
        freight.setId(count.incrementAndGet());

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreightMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, freightDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(freightDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFreight() throws Exception {
        int databaseSizeBeforeUpdate = freightRepository.findAll().size();
        freight.setId(count.incrementAndGet());

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(freightDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFreight() throws Exception {
        int databaseSizeBeforeUpdate = freightRepository.findAll().size();
        freight.setId(count.incrementAndGet());

        // Create the Freight
        FreightDTO freightDTO = freightMapper.toDto(freight);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(freightDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Freight in the database
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFreight() throws Exception {
        // Initialize the database
        freightRepository.saveAndFlush(freight);

        int databaseSizeBeforeDelete = freightRepository.findAll().size();

        // Delete the freight
        restFreightMockMvc
            .perform(delete(ENTITY_API_URL_ID, freight.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Freight> freightList = freightRepository.findAll();
        assertThat(freightList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
