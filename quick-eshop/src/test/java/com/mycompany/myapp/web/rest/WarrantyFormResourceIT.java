package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.WarrantyForm;
import com.mycompany.myapp.repository.WarrantyFormRepository;
import com.mycompany.myapp.service.dto.WarrantyFormDTO;
import com.mycompany.myapp.service.mapper.WarrantyFormMapper;
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
 * Integration tests for the {@link WarrantyFormResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WarrantyFormResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Double DEFAULT_QUANTITY = 1D;
    private static final Double UPDATED_QUANTITY = 2D;

    private static final String ENTITY_API_URL = "/api/warranty-forms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private WarrantyFormRepository warrantyFormRepository;

    @Autowired
    private WarrantyFormMapper warrantyFormMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWarrantyFormMockMvc;

    private WarrantyForm warrantyForm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WarrantyForm createEntity(EntityManager em) {
        WarrantyForm warrantyForm = new WarrantyForm().name(DEFAULT_NAME).quantity(DEFAULT_QUANTITY);
        return warrantyForm;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WarrantyForm createUpdatedEntity(EntityManager em) {
        WarrantyForm warrantyForm = new WarrantyForm().name(UPDATED_NAME).quantity(UPDATED_QUANTITY);
        return warrantyForm;
    }

    @BeforeEach
    public void initTest() {
        warrantyForm = createEntity(em);
    }

    @Test
    @Transactional
    void createWarrantyForm() throws Exception {
        int databaseSizeBeforeCreate = warrantyFormRepository.findAll().size();
        // Create the WarrantyForm
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);
        restWarrantyFormMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isCreated());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeCreate + 1);
        WarrantyForm testWarrantyForm = warrantyFormList.get(warrantyFormList.size() - 1);
        assertThat(testWarrantyForm.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWarrantyForm.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void createWarrantyFormWithExistingId() throws Exception {
        // Create the WarrantyForm with an existing ID
        warrantyForm.setId(1L);
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);

        int databaseSizeBeforeCreate = warrantyFormRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWarrantyFormMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWarrantyForms() throws Exception {
        // Initialize the database
        warrantyFormRepository.saveAndFlush(warrantyForm);

        // Get all the warrantyFormList
        restWarrantyFormMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(warrantyForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())));
    }

    @Test
    @Transactional
    void getWarrantyForm() throws Exception {
        // Initialize the database
        warrantyFormRepository.saveAndFlush(warrantyForm);

        // Get the warrantyForm
        restWarrantyFormMockMvc
            .perform(get(ENTITY_API_URL_ID, warrantyForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(warrantyForm.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingWarrantyForm() throws Exception {
        // Get the warrantyForm
        restWarrantyFormMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewWarrantyForm() throws Exception {
        // Initialize the database
        warrantyFormRepository.saveAndFlush(warrantyForm);

        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();

        // Update the warrantyForm
        WarrantyForm updatedWarrantyForm = warrantyFormRepository.findById(warrantyForm.getId()).get();
        // Disconnect from session so that the updates on updatedWarrantyForm are not directly saved in db
        em.detach(updatedWarrantyForm);
        updatedWarrantyForm.name(UPDATED_NAME).quantity(UPDATED_QUANTITY);
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(updatedWarrantyForm);

        restWarrantyFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, warrantyFormDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isOk());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
        WarrantyForm testWarrantyForm = warrantyFormList.get(warrantyFormList.size() - 1);
        assertThat(testWarrantyForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWarrantyForm.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void putNonExistingWarrantyForm() throws Exception {
        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();
        warrantyForm.setId(count.incrementAndGet());

        // Create the WarrantyForm
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWarrantyFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, warrantyFormDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWarrantyForm() throws Exception {
        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();
        warrantyForm.setId(count.incrementAndGet());

        // Create the WarrantyForm
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWarrantyForm() throws Exception {
        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();
        warrantyForm.setId(count.incrementAndGet());

        // Create the WarrantyForm
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyFormMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWarrantyFormWithPatch() throws Exception {
        // Initialize the database
        warrantyFormRepository.saveAndFlush(warrantyForm);

        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();

        // Update the warrantyForm using partial update
        WarrantyForm partialUpdatedWarrantyForm = new WarrantyForm();
        partialUpdatedWarrantyForm.setId(warrantyForm.getId());

        partialUpdatedWarrantyForm.name(UPDATED_NAME);

        restWarrantyFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWarrantyForm.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWarrantyForm))
            )
            .andExpect(status().isOk());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
        WarrantyForm testWarrantyForm = warrantyFormList.get(warrantyFormList.size() - 1);
        assertThat(testWarrantyForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWarrantyForm.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    void fullUpdateWarrantyFormWithPatch() throws Exception {
        // Initialize the database
        warrantyFormRepository.saveAndFlush(warrantyForm);

        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();

        // Update the warrantyForm using partial update
        WarrantyForm partialUpdatedWarrantyForm = new WarrantyForm();
        partialUpdatedWarrantyForm.setId(warrantyForm.getId());

        partialUpdatedWarrantyForm.name(UPDATED_NAME).quantity(UPDATED_QUANTITY);

        restWarrantyFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWarrantyForm.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedWarrantyForm))
            )
            .andExpect(status().isOk());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
        WarrantyForm testWarrantyForm = warrantyFormList.get(warrantyFormList.size() - 1);
        assertThat(testWarrantyForm.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWarrantyForm.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    void patchNonExistingWarrantyForm() throws Exception {
        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();
        warrantyForm.setId(count.incrementAndGet());

        // Create the WarrantyForm
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWarrantyFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, warrantyFormDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWarrantyForm() throws Exception {
        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();
        warrantyForm.setId(count.incrementAndGet());

        // Create the WarrantyForm
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWarrantyForm() throws Exception {
        int databaseSizeBeforeUpdate = warrantyFormRepository.findAll().size();
        warrantyForm.setId(count.incrementAndGet());

        // Create the WarrantyForm
        WarrantyFormDTO warrantyFormDTO = warrantyFormMapper.toDto(warrantyForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWarrantyFormMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(warrantyFormDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the WarrantyForm in the database
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWarrantyForm() throws Exception {
        // Initialize the database
        warrantyFormRepository.saveAndFlush(warrantyForm);

        int databaseSizeBeforeDelete = warrantyFormRepository.findAll().size();

        // Delete the warrantyForm
        restWarrantyFormMockMvc
            .perform(delete(ENTITY_API_URL_ID, warrantyForm.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WarrantyForm> warrantyFormList = warrantyFormRepository.findAll();
        assertThat(warrantyFormList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
