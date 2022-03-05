package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.CoupomForm;
import com.mycompany.myapp.repository.CoupomFormRepository;
import com.mycompany.myapp.service.dto.CoupomFormDTO;
import com.mycompany.myapp.service.mapper.CoupomFormMapper;
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
 * Integration tests for the {@link CoupomFormResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CoupomFormResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Float DEFAULT_DESCONTO = 1F;
    private static final Float UPDATED_DESCONTO = 2F;

    private static final String ENTITY_API_URL = "/api/coupom-forms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CoupomFormRepository coupomFormRepository;

    @Autowired
    private CoupomFormMapper coupomFormMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCoupomFormMockMvc;

    private CoupomForm coupomForm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoupomForm createEntity(EntityManager em) {
        CoupomForm coupomForm = new CoupomForm().nome(DEFAULT_NOME).desconto(DEFAULT_DESCONTO);
        return coupomForm;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CoupomForm createUpdatedEntity(EntityManager em) {
        CoupomForm coupomForm = new CoupomForm().nome(UPDATED_NOME).desconto(UPDATED_DESCONTO);
        return coupomForm;
    }

    @BeforeEach
    public void initTest() {
        coupomForm = createEntity(em);
    }

    @Test
    @Transactional
    void createCoupomForm() throws Exception {
        int databaseSizeBeforeCreate = coupomFormRepository.findAll().size();
        // Create the CoupomForm
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);
        restCoupomFormMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coupomFormDTO)))
            .andExpect(status().isCreated());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeCreate + 1);
        CoupomForm testCoupomForm = coupomFormList.get(coupomFormList.size() - 1);
        assertThat(testCoupomForm.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCoupomForm.getDesconto()).isEqualTo(DEFAULT_DESCONTO);
    }

    @Test
    @Transactional
    void createCoupomFormWithExistingId() throws Exception {
        // Create the CoupomForm with an existing ID
        coupomForm.setId(1L);
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);

        int databaseSizeBeforeCreate = coupomFormRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoupomFormMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coupomFormDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCoupomForms() throws Exception {
        // Initialize the database
        coupomFormRepository.saveAndFlush(coupomForm);

        // Get all the coupomFormList
        restCoupomFormMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coupomForm.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].desconto").value(hasItem(DEFAULT_DESCONTO.doubleValue())));
    }

    @Test
    @Transactional
    void getCoupomForm() throws Exception {
        // Initialize the database
        coupomFormRepository.saveAndFlush(coupomForm);

        // Get the coupomForm
        restCoupomFormMockMvc
            .perform(get(ENTITY_API_URL_ID, coupomForm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(coupomForm.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.desconto").value(DEFAULT_DESCONTO.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingCoupomForm() throws Exception {
        // Get the coupomForm
        restCoupomFormMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCoupomForm() throws Exception {
        // Initialize the database
        coupomFormRepository.saveAndFlush(coupomForm);

        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();

        // Update the coupomForm
        CoupomForm updatedCoupomForm = coupomFormRepository.findById(coupomForm.getId()).get();
        // Disconnect from session so that the updates on updatedCoupomForm are not directly saved in db
        em.detach(updatedCoupomForm);
        updatedCoupomForm.nome(UPDATED_NOME).desconto(UPDATED_DESCONTO);
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(updatedCoupomForm);

        restCoupomFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, coupomFormDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coupomFormDTO))
            )
            .andExpect(status().isOk());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
        CoupomForm testCoupomForm = coupomFormList.get(coupomFormList.size() - 1);
        assertThat(testCoupomForm.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCoupomForm.getDesconto()).isEqualTo(UPDATED_DESCONTO);
    }

    @Test
    @Transactional
    void putNonExistingCoupomForm() throws Exception {
        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();
        coupomForm.setId(count.incrementAndGet());

        // Create the CoupomForm
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoupomFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, coupomFormDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coupomFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCoupomForm() throws Exception {
        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();
        coupomForm.setId(count.incrementAndGet());

        // Create the CoupomForm
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomFormMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coupomFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCoupomForm() throws Exception {
        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();
        coupomForm.setId(count.incrementAndGet());

        // Create the CoupomForm
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomFormMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coupomFormDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCoupomFormWithPatch() throws Exception {
        // Initialize the database
        coupomFormRepository.saveAndFlush(coupomForm);

        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();

        // Update the coupomForm using partial update
        CoupomForm partialUpdatedCoupomForm = new CoupomForm();
        partialUpdatedCoupomForm.setId(coupomForm.getId());

        partialUpdatedCoupomForm.nome(UPDATED_NOME);

        restCoupomFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCoupomForm.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCoupomForm))
            )
            .andExpect(status().isOk());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
        CoupomForm testCoupomForm = coupomFormList.get(coupomFormList.size() - 1);
        assertThat(testCoupomForm.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCoupomForm.getDesconto()).isEqualTo(DEFAULT_DESCONTO);
    }

    @Test
    @Transactional
    void fullUpdateCoupomFormWithPatch() throws Exception {
        // Initialize the database
        coupomFormRepository.saveAndFlush(coupomForm);

        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();

        // Update the coupomForm using partial update
        CoupomForm partialUpdatedCoupomForm = new CoupomForm();
        partialUpdatedCoupomForm.setId(coupomForm.getId());

        partialUpdatedCoupomForm.nome(UPDATED_NOME).desconto(UPDATED_DESCONTO);

        restCoupomFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCoupomForm.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCoupomForm))
            )
            .andExpect(status().isOk());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
        CoupomForm testCoupomForm = coupomFormList.get(coupomFormList.size() - 1);
        assertThat(testCoupomForm.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCoupomForm.getDesconto()).isEqualTo(UPDATED_DESCONTO);
    }

    @Test
    @Transactional
    void patchNonExistingCoupomForm() throws Exception {
        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();
        coupomForm.setId(count.incrementAndGet());

        // Create the CoupomForm
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoupomFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, coupomFormDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coupomFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCoupomForm() throws Exception {
        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();
        coupomForm.setId(count.incrementAndGet());

        // Create the CoupomForm
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomFormMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coupomFormDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCoupomForm() throws Exception {
        int databaseSizeBeforeUpdate = coupomFormRepository.findAll().size();
        coupomForm.setId(count.incrementAndGet());

        // Create the CoupomForm
        CoupomFormDTO coupomFormDTO = coupomFormMapper.toDto(coupomForm);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomFormMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(coupomFormDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CoupomForm in the database
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCoupomForm() throws Exception {
        // Initialize the database
        coupomFormRepository.saveAndFlush(coupomForm);

        int databaseSizeBeforeDelete = coupomFormRepository.findAll().size();

        // Delete the coupomForm
        restCoupomFormMockMvc
            .perform(delete(ENTITY_API_URL_ID, coupomForm.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CoupomForm> coupomFormList = coupomFormRepository.findAll();
        assertThat(coupomFormList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
