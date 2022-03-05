package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Coupom;
import com.mycompany.myapp.repository.CoupomRepository;
import com.mycompany.myapp.service.dto.CoupomDTO;
import com.mycompany.myapp.service.mapper.CoupomMapper;
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
 * Integration tests for the {@link CoupomResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CoupomResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Float DEFAULT_DESCONTO = 1F;
    private static final Float UPDATED_DESCONTO = 2F;

    private static final String ENTITY_API_URL = "/api/coupoms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CoupomRepository coupomRepository;

    @Autowired
    private CoupomMapper coupomMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCoupomMockMvc;

    private Coupom coupom;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Coupom createEntity(EntityManager em) {
        Coupom coupom = new Coupom().nome(DEFAULT_NOME).desconto(DEFAULT_DESCONTO);
        return coupom;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Coupom createUpdatedEntity(EntityManager em) {
        Coupom coupom = new Coupom().nome(UPDATED_NOME).desconto(UPDATED_DESCONTO);
        return coupom;
    }

    @BeforeEach
    public void initTest() {
        coupom = createEntity(em);
    }

    @Test
    @Transactional
    void createCoupom() throws Exception {
        int databaseSizeBeforeCreate = coupomRepository.findAll().size();
        // Create the Coupom
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);
        restCoupomMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coupomDTO)))
            .andExpect(status().isCreated());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeCreate + 1);
        Coupom testCoupom = coupomList.get(coupomList.size() - 1);
        assertThat(testCoupom.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testCoupom.getDesconto()).isEqualTo(DEFAULT_DESCONTO);
    }

    @Test
    @Transactional
    void createCoupomWithExistingId() throws Exception {
        // Create the Coupom with an existing ID
        coupom.setId(1L);
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);

        int databaseSizeBeforeCreate = coupomRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoupomMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coupomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCoupoms() throws Exception {
        // Initialize the database
        coupomRepository.saveAndFlush(coupom);

        // Get all the coupomList
        restCoupomMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(coupom.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].desconto").value(hasItem(DEFAULT_DESCONTO.doubleValue())));
    }

    @Test
    @Transactional
    void getCoupom() throws Exception {
        // Initialize the database
        coupomRepository.saveAndFlush(coupom);

        // Get the coupom
        restCoupomMockMvc
            .perform(get(ENTITY_API_URL_ID, coupom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(coupom.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.desconto").value(DEFAULT_DESCONTO.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingCoupom() throws Exception {
        // Get the coupom
        restCoupomMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCoupom() throws Exception {
        // Initialize the database
        coupomRepository.saveAndFlush(coupom);

        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();

        // Update the coupom
        Coupom updatedCoupom = coupomRepository.findById(coupom.getId()).get();
        // Disconnect from session so that the updates on updatedCoupom are not directly saved in db
        em.detach(updatedCoupom);
        updatedCoupom.nome(UPDATED_NOME).desconto(UPDATED_DESCONTO);
        CoupomDTO coupomDTO = coupomMapper.toDto(updatedCoupom);

        restCoupomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, coupomDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coupomDTO))
            )
            .andExpect(status().isOk());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
        Coupom testCoupom = coupomList.get(coupomList.size() - 1);
        assertThat(testCoupom.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCoupom.getDesconto()).isEqualTo(UPDATED_DESCONTO);
    }

    @Test
    @Transactional
    void putNonExistingCoupom() throws Exception {
        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();
        coupom.setId(count.incrementAndGet());

        // Create the Coupom
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoupomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, coupomDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coupomDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCoupom() throws Exception {
        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();
        coupom.setId(count.incrementAndGet());

        // Create the Coupom
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coupomDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCoupom() throws Exception {
        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();
        coupom.setId(count.incrementAndGet());

        // Create the Coupom
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coupomDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCoupomWithPatch() throws Exception {
        // Initialize the database
        coupomRepository.saveAndFlush(coupom);

        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();

        // Update the coupom using partial update
        Coupom partialUpdatedCoupom = new Coupom();
        partialUpdatedCoupom.setId(coupom.getId());

        partialUpdatedCoupom.nome(UPDATED_NOME);

        restCoupomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCoupom.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCoupom))
            )
            .andExpect(status().isOk());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
        Coupom testCoupom = coupomList.get(coupomList.size() - 1);
        assertThat(testCoupom.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCoupom.getDesconto()).isEqualTo(DEFAULT_DESCONTO);
    }

    @Test
    @Transactional
    void fullUpdateCoupomWithPatch() throws Exception {
        // Initialize the database
        coupomRepository.saveAndFlush(coupom);

        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();

        // Update the coupom using partial update
        Coupom partialUpdatedCoupom = new Coupom();
        partialUpdatedCoupom.setId(coupom.getId());

        partialUpdatedCoupom.nome(UPDATED_NOME).desconto(UPDATED_DESCONTO);

        restCoupomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCoupom.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCoupom))
            )
            .andExpect(status().isOk());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
        Coupom testCoupom = coupomList.get(coupomList.size() - 1);
        assertThat(testCoupom.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testCoupom.getDesconto()).isEqualTo(UPDATED_DESCONTO);
    }

    @Test
    @Transactional
    void patchNonExistingCoupom() throws Exception {
        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();
        coupom.setId(count.incrementAndGet());

        // Create the Coupom
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoupomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, coupomDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coupomDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCoupom() throws Exception {
        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();
        coupom.setId(count.incrementAndGet());

        // Create the Coupom
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coupomDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCoupom() throws Exception {
        int databaseSizeBeforeUpdate = coupomRepository.findAll().size();
        coupom.setId(count.incrementAndGet());

        // Create the Coupom
        CoupomDTO coupomDTO = coupomMapper.toDto(coupom);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoupomMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(coupomDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Coupom in the database
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCoupom() throws Exception {
        // Initialize the database
        coupomRepository.saveAndFlush(coupom);

        int databaseSizeBeforeDelete = coupomRepository.findAll().size();

        // Delete the coupom
        restCoupomMockMvc
            .perform(delete(ENTITY_API_URL_ID, coupom.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Coupom> coupomList = coupomRepository.findAll();
        assertThat(coupomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
