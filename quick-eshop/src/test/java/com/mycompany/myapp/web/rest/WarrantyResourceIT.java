package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Warranty;
import com.mycompany.myapp.repository.WarrantyRepository;
import com.mycompany.myapp.service.dto.WarrantyDTO;
import com.mycompany.myapp.service.mapper.WarrantyMapper;
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
 * Integration tests for the {@link WarrantyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WarrantyResourceIT {

    private static final String DEFAULT_TEMPO = "AAAAAAAAAA";
    private static final String UPDATED_TEMPO = "BBBBBBBBBB";

    private static final Float DEFAULT_VALOR = 1F;
    private static final Float UPDATED_VALOR = 2F;

    private static final String ENTITY_API_URL = "/api/warranties";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WarrantyRepository warrantyRepository;

    @Autowired
    private WarrantyMapper warrantyMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWarrantyMockMvc;

    private Warranty warranty;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Warranty createEntity(EntityManager em) {
        Warranty warranty = new Warranty().tempo(DEFAULT_TEMPO).valor(DEFAULT_VALOR);
        return warranty;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Warranty createUpdatedEntity(EntityManager em) {
        Warranty warranty = new Warranty().tempo(UPDATED_TEMPO).valor(UPDATED_VALOR);
        return warranty;
    }

    @BeforeEach
    public void initTest() {
        warranty = createEntity(em);
    }

    @Test
    @Transactional
    void createWarranty() throws Exception {
        int databaseSizeBeforeCreate = warrantyRepository.findAll().size();
        // Create the Warranty
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);
        restWarrantyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(warrantyDTO)))
            .andExpect(status().isCreated());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeCreate + 1);
        Warranty testWarranty = warrantyList.get(warrantyList.size() - 1);
        assertThat(testWarranty.getTempo()).isEqualTo(DEFAULT_TEMPO);
        assertThat(testWarranty.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    void createWarrantyWithExistingId() throws Exception {
        // Create the Warranty with an existing ID
        warranty.setId(1L);
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);

        int databaseSizeBeforeCreate = warrantyRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWarrantyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(warrantyDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWarranties() throws Exception {
        // Initialize the database
        warrantyRepository.saveAndFlush(warranty);

        // Get all the warrantyList
        restWarrantyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(warranty.getId().intValue())))
            .andExpect(jsonPath("$.[*].tempo").value(hasItem(DEFAULT_TEMPO)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())));
    }

    @Test
    @Transactional
    void getWarranty() throws Exception {
        // Initialize the database
        warrantyRepository.saveAndFlush(warranty);

        // Get the warranty
        restWarrantyMockMvc
            .perform(get(ENTITY_API_URL_ID, warranty.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(warranty.getId().intValue()))
            .andExpect(jsonPath("$.tempo").value(DEFAULT_TEMPO))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingWarranty() throws Exception {
        // Get the warranty
        restWarrantyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWarranty() throws Exception {
        // Initialize the database
        warrantyRepository.saveAndFlush(warranty);

        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();

        // Update the warranty
        Warranty updatedWarranty = warrantyRepository.findById(warranty.getId()).get();
        // Disconnect from session so that the updates on updatedWarranty are not directly saved in db
        em.detach(updatedWarranty);
        updatedWarranty.tempo(UPDATED_TEMPO).valor(UPDATED_VALOR);
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(updatedWarranty);

        restWarrantyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, warrantyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(warrantyDTO))
            )
            .andExpect(status().isOk());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
        Warranty testWarranty = warrantyList.get(warrantyList.size() - 1);
        assertThat(testWarranty.getTempo()).isEqualTo(UPDATED_TEMPO);
        assertThat(testWarranty.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void putNonExistingWarranty() throws Exception {
        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();
        warranty.setId(count.incrementAndGet());

        // Create the Warranty
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWarrantyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, warrantyDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(warrantyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWarranty() throws Exception {
        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();
        warranty.setId(count.incrementAndGet());

        // Create the Warranty
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(warrantyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWarranty() throws Exception {
        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();
        warranty.setId(count.incrementAndGet());

        // Create the Warranty
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(warrantyDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWarrantyWithPatch() throws Exception {
        // Initialize the database
        warrantyRepository.saveAndFlush(warranty);

        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();

        // Update the warranty using partial update
        Warranty partialUpdatedWarranty = new Warranty();
        partialUpdatedWarranty.setId(warranty.getId());

        partialUpdatedWarranty.tempo(UPDATED_TEMPO);

        restWarrantyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWarranty.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWarranty))
            )
            .andExpect(status().isOk());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
        Warranty testWarranty = warrantyList.get(warrantyList.size() - 1);
        assertThat(testWarranty.getTempo()).isEqualTo(UPDATED_TEMPO);
        assertThat(testWarranty.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    void fullUpdateWarrantyWithPatch() throws Exception {
        // Initialize the database
        warrantyRepository.saveAndFlush(warranty);

        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();

        // Update the warranty using partial update
        Warranty partialUpdatedWarranty = new Warranty();
        partialUpdatedWarranty.setId(warranty.getId());

        partialUpdatedWarranty.tempo(UPDATED_TEMPO).valor(UPDATED_VALOR);

        restWarrantyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWarranty.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWarranty))
            )
            .andExpect(status().isOk());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
        Warranty testWarranty = warrantyList.get(warrantyList.size() - 1);
        assertThat(testWarranty.getTempo()).isEqualTo(UPDATED_TEMPO);
        assertThat(testWarranty.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    void patchNonExistingWarranty() throws Exception {
        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();
        warranty.setId(count.incrementAndGet());

        // Create the Warranty
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWarrantyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, warrantyDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(warrantyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWarranty() throws Exception {
        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();
        warranty.setId(count.incrementAndGet());

        // Create the Warranty
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(warrantyDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWarranty() throws Exception {
        int databaseSizeBeforeUpdate = warrantyRepository.findAll().size();
        warranty.setId(count.incrementAndGet());

        // Create the Warranty
        WarrantyDTO warrantyDTO = warrantyMapper.toDto(warranty);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(warrantyDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Warranty in the database
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWarranty() throws Exception {
        // Initialize the database
        warrantyRepository.saveAndFlush(warranty);

        int databaseSizeBeforeDelete = warrantyRepository.findAll().size();

        // Delete the warranty
        restWarrantyMockMvc
            .perform(delete(ENTITY_API_URL_ID, warranty.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Warranty> warrantyList = warrantyRepository.findAll();
        assertThat(warrantyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
