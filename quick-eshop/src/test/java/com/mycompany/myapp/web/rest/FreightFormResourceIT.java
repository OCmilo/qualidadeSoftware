package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.FreightForm;
import com.mycompany.myapp.repository.FreightFormRepository;
import com.mycompany.myapp.service.dto.FreightFormDTO;
import com.mycompany.myapp.service.mapper.FreightFormMapper;
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
 * Integration tests for the {@link FreightFormResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FreightFormResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    private static final String ENTITY_API_URL = "/api/freight-forms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FreightFormRepository freightFormRepository;

    @Autowired
    private FreightFormMapper freightFormMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFreightFormMockMvc;

    private FreightForm freightForm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FreightForm createEntity(EntityManager em) {
        FreightForm freightForm = new FreightForm().name(DEFAULT_NAME).quantity(DEFAULT_QUANTITY);
        return freightForm;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FreightForm createUpdatedEntity(EntityManager em) {
        FreightForm freightForm = new FreightForm().name(UPDATED_NAME).quantity(UPDATED_QUANTITY);
        return freightForm;
    }

    @BeforeEach
    public void initTest() {
        freightForm = createEntity(em);
    }

    @Test
    @Transactional
    void createFreightForm() throws Exception {
        int databaseSizeBeforeCreate = freightFormRepository.findAll().size();
        // Create the FreightForm
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);
        restFreightFormMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isCreated());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeCreate + 1);
        FreightForm testFreightForm = freightFormList.get(freightFormList.size() - 1);
        assertThat(testFreightForm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFreightForm.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void createFreightFormWithExistingId() throws Exception {
        // Create the FreightForm with an existing ID
        freightForm.setId(1L);
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);

        int databaseSizeBeforeCreate = freightFormRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreightFormMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFreightForms() throws Exception {
        // Initialize the database
        freightFormRepository.saveAndFlush(freightForm);

        // Get all the freightFormList
        restFreightFormMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freightForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())));
    }

    @Test
    @Transactional
    void getFreightForm() throws Exception {
        // Initialize the database
        freightFormRepository.saveAndFlush(freightForm);

        // Get the freightForm
        restFreightFormMockMvc
            .perform(get(ENTITY_API_URL_ID, freightForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(freightForm.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingFreightForm() throws Exception {
        // Get the freightForm
        restFreightFormMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewFreightForm() throws Exception {
        // Initialize the database
        freightFormRepository.saveAndFlush(freightForm);

        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();

        // Update the freightForm
        FreightForm updatedFreightForm = freightFormRepository.findById(freightForm.getId()).get();
        // Disconnect from session so that the updates on updatedFreightForm are not directly saved in db
        em.detach(updatedFreightForm);
        updatedFreightForm.name(UPDATED_NAME).quantity(UPDATED_QUANTITY);
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(updatedFreightForm);

        restFreightFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, freightFormDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isOk());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
        FreightForm testFreightForm = freightFormList.get(freightFormList.size() - 1);
        assertThat(testFreightForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFreightForm.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void putNonExistingFreightForm() throws Exception {
        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();
        freightForm.setId(count.incrementAndGet());

        // Create the FreightForm
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreightFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, freightFormDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFreightForm() throws Exception {
        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();
        freightForm.setId(count.incrementAndGet());

        // Create the FreightForm
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFreightForm() throws Exception {
        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();
        freightForm.setId(count.incrementAndGet());

        // Create the FreightForm
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightFormMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(freightFormDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFreightFormWithPatch() throws Exception {
        // Initialize the database
        freightFormRepository.saveAndFlush(freightForm);

        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();

        // Update the freightForm using partial update
        FreightForm partialUpdatedFreightForm = new FreightForm();
        partialUpdatedFreightForm.setId(freightForm.getId());

        partialUpdatedFreightForm.quantity(UPDATED_QUANTITY);

        restFreightFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFreightForm.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFreightForm))
            )
            .andExpect(status().isOk());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
        FreightForm testFreightForm = freightFormList.get(freightFormList.size() - 1);
        assertThat(testFreightForm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testFreightForm.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void fullUpdateFreightFormWithPatch() throws Exception {
        // Initialize the database
        freightFormRepository.saveAndFlush(freightForm);

        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();

        // Update the freightForm using partial update
        FreightForm partialUpdatedFreightForm = new FreightForm();
        partialUpdatedFreightForm.setId(freightForm.getId());

        partialUpdatedFreightForm.name(UPDATED_NAME).quantity(UPDATED_QUANTITY);

        restFreightFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFreightForm.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedFreightForm))
            )
            .andExpect(status().isOk());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
        FreightForm testFreightForm = freightFormList.get(freightFormList.size() - 1);
        assertThat(testFreightForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testFreightForm.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void patchNonExistingFreightForm() throws Exception {
        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();
        freightForm.setId(count.incrementAndGet());

        // Create the FreightForm
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreightFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, freightFormDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFreightForm() throws Exception {
        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();
        freightForm.setId(count.incrementAndGet());

        // Create the FreightForm
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFreightForm() throws Exception {
        int databaseSizeBeforeUpdate = freightFormRepository.findAll().size();
        freightForm.setId(count.incrementAndGet());

        // Create the FreightForm
        FreightFormDTO freightFormDTO = freightFormMapper.toDto(freightForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFreightFormMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(freightFormDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the FreightForm in the database
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFreightForm() throws Exception {
        // Initialize the database
        freightFormRepository.saveAndFlush(freightForm);

        int databaseSizeBeforeDelete = freightFormRepository.findAll().size();

        // Delete the freightForm
        restFreightFormMockMvc
            .perform(delete(ENTITY_API_URL_ID, freightForm.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FreightForm> freightFormList = freightFormRepository.findAll();
        assertThat(freightFormList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
