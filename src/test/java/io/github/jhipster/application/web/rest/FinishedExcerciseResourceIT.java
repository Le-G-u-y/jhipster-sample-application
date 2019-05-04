package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FinishedExcercise;
import io.github.jhipster.application.repository.FinishedExcerciseRepository;
import io.github.jhipster.application.service.FinishedExcerciseService;
import io.github.jhipster.application.service.dto.FinishedExcerciseDTO;
import io.github.jhipster.application.service.mapper.FinishedExcerciseMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link FinishedExcerciseResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FinishedExcerciseResourceIT {

    private static final Integer DEFAULT_ACTUAL_BPM = 1;
    private static final Integer UPDATED_ACTUAL_BPM = 2;

    private static final Integer DEFAULT_ACTUAL_MINUTES = 1;
    private static final Integer UPDATED_ACTUAL_MINUTES = 2;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FinishedExcerciseRepository finishedExcerciseRepository;

    @Autowired
    private FinishedExcerciseMapper finishedExcerciseMapper;

    @Autowired
    private FinishedExcerciseService finishedExcerciseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restFinishedExcerciseMockMvc;

    private FinishedExcercise finishedExcercise;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FinishedExcerciseResource finishedExcerciseResource = new FinishedExcerciseResource(finishedExcerciseService);
        this.restFinishedExcerciseMockMvc = MockMvcBuilders.standaloneSetup(finishedExcerciseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinishedExcercise createEntity(EntityManager em) {
        FinishedExcercise finishedExcercise = new FinishedExcercise()
            .actualBpm(DEFAULT_ACTUAL_BPM)
            .actualMinutes(DEFAULT_ACTUAL_MINUTES)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return finishedExcercise;
    }

    @BeforeEach
    public void initTest() {
        finishedExcercise = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinishedExcercise() throws Exception {
        int databaseSizeBeforeCreate = finishedExcerciseRepository.findAll().size();

        // Create the FinishedExcercise
        FinishedExcerciseDTO finishedExcerciseDTO = finishedExcerciseMapper.toDto(finishedExcercise);
        restFinishedExcerciseMockMvc.perform(post("/api/finished-excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedExcerciseDTO)))
            .andExpect(status().isCreated());

        // Validate the FinishedExcercise in the database
        List<FinishedExcercise> finishedExcerciseList = finishedExcerciseRepository.findAll();
        assertThat(finishedExcerciseList).hasSize(databaseSizeBeforeCreate + 1);
        FinishedExcercise testFinishedExcercise = finishedExcerciseList.get(finishedExcerciseList.size() - 1);
        assertThat(testFinishedExcercise.getActualBpm()).isEqualTo(DEFAULT_ACTUAL_BPM);
        assertThat(testFinishedExcercise.getActualMinutes()).isEqualTo(DEFAULT_ACTUAL_MINUTES);
        assertThat(testFinishedExcercise.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testFinishedExcercise.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void createFinishedExcerciseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = finishedExcerciseRepository.findAll().size();

        // Create the FinishedExcercise with an existing ID
        finishedExcercise.setId(1L);
        FinishedExcerciseDTO finishedExcerciseDTO = finishedExcerciseMapper.toDto(finishedExcercise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinishedExcerciseMockMvc.perform(post("/api/finished-excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedExcerciseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinishedExcercise in the database
        List<FinishedExcercise> finishedExcerciseList = finishedExcerciseRepository.findAll();
        assertThat(finishedExcerciseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = finishedExcerciseRepository.findAll().size();
        // set the field null
        finishedExcercise.setCreateDate(null);

        // Create the FinishedExcercise, which fails.
        FinishedExcerciseDTO finishedExcerciseDTO = finishedExcerciseMapper.toDto(finishedExcercise);

        restFinishedExcerciseMockMvc.perform(post("/api/finished-excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedExcerciseDTO)))
            .andExpect(status().isBadRequest());

        List<FinishedExcercise> finishedExcerciseList = finishedExcerciseRepository.findAll();
        assertThat(finishedExcerciseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifyDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = finishedExcerciseRepository.findAll().size();
        // set the field null
        finishedExcercise.setModifyDate(null);

        // Create the FinishedExcercise, which fails.
        FinishedExcerciseDTO finishedExcerciseDTO = finishedExcerciseMapper.toDto(finishedExcercise);

        restFinishedExcerciseMockMvc.perform(post("/api/finished-excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedExcerciseDTO)))
            .andExpect(status().isBadRequest());

        List<FinishedExcercise> finishedExcerciseList = finishedExcerciseRepository.findAll();
        assertThat(finishedExcerciseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFinishedExcercises() throws Exception {
        // Initialize the database
        finishedExcerciseRepository.saveAndFlush(finishedExcercise);

        // Get all the finishedExcerciseList
        restFinishedExcerciseMockMvc.perform(get("/api/finished-excercises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(finishedExcercise.getId().intValue())))
            .andExpect(jsonPath("$.[*].actualBpm").value(hasItem(DEFAULT_ACTUAL_BPM)))
            .andExpect(jsonPath("$.[*].actualMinutes").value(hasItem(DEFAULT_ACTUAL_MINUTES)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getFinishedExcercise() throws Exception {
        // Initialize the database
        finishedExcerciseRepository.saveAndFlush(finishedExcercise);

        // Get the finishedExcercise
        restFinishedExcerciseMockMvc.perform(get("/api/finished-excercises/{id}", finishedExcercise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(finishedExcercise.getId().intValue()))
            .andExpect(jsonPath("$.actualBpm").value(DEFAULT_ACTUAL_BPM))
            .andExpect(jsonPath("$.actualMinutes").value(DEFAULT_ACTUAL_MINUTES))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFinishedExcercise() throws Exception {
        // Get the finishedExcercise
        restFinishedExcerciseMockMvc.perform(get("/api/finished-excercises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinishedExcercise() throws Exception {
        // Initialize the database
        finishedExcerciseRepository.saveAndFlush(finishedExcercise);

        int databaseSizeBeforeUpdate = finishedExcerciseRepository.findAll().size();

        // Update the finishedExcercise
        FinishedExcercise updatedFinishedExcercise = finishedExcerciseRepository.findById(finishedExcercise.getId()).get();
        // Disconnect from session so that the updates on updatedFinishedExcercise are not directly saved in db
        em.detach(updatedFinishedExcercise);
        updatedFinishedExcercise
            .actualBpm(UPDATED_ACTUAL_BPM)
            .actualMinutes(UPDATED_ACTUAL_MINUTES)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        FinishedExcerciseDTO finishedExcerciseDTO = finishedExcerciseMapper.toDto(updatedFinishedExcercise);

        restFinishedExcerciseMockMvc.perform(put("/api/finished-excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedExcerciseDTO)))
            .andExpect(status().isOk());

        // Validate the FinishedExcercise in the database
        List<FinishedExcercise> finishedExcerciseList = finishedExcerciseRepository.findAll();
        assertThat(finishedExcerciseList).hasSize(databaseSizeBeforeUpdate);
        FinishedExcercise testFinishedExcercise = finishedExcerciseList.get(finishedExcerciseList.size() - 1);
        assertThat(testFinishedExcercise.getActualBpm()).isEqualTo(UPDATED_ACTUAL_BPM);
        assertThat(testFinishedExcercise.getActualMinutes()).isEqualTo(UPDATED_ACTUAL_MINUTES);
        assertThat(testFinishedExcercise.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testFinishedExcercise.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFinishedExcercise() throws Exception {
        int databaseSizeBeforeUpdate = finishedExcerciseRepository.findAll().size();

        // Create the FinishedExcercise
        FinishedExcerciseDTO finishedExcerciseDTO = finishedExcerciseMapper.toDto(finishedExcercise);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinishedExcerciseMockMvc.perform(put("/api/finished-excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedExcerciseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinishedExcercise in the database
        List<FinishedExcercise> finishedExcerciseList = finishedExcerciseRepository.findAll();
        assertThat(finishedExcerciseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinishedExcercise() throws Exception {
        // Initialize the database
        finishedExcerciseRepository.saveAndFlush(finishedExcercise);

        int databaseSizeBeforeDelete = finishedExcerciseRepository.findAll().size();

        // Delete the finishedExcercise
        restFinishedExcerciseMockMvc.perform(delete("/api/finished-excercises/{id}", finishedExcercise.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FinishedExcercise> finishedExcerciseList = finishedExcerciseRepository.findAll();
        assertThat(finishedExcerciseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinishedExcercise.class);
        FinishedExcercise finishedExcercise1 = new FinishedExcercise();
        finishedExcercise1.setId(1L);
        FinishedExcercise finishedExcercise2 = new FinishedExcercise();
        finishedExcercise2.setId(finishedExcercise1.getId());
        assertThat(finishedExcercise1).isEqualTo(finishedExcercise2);
        finishedExcercise2.setId(2L);
        assertThat(finishedExcercise1).isNotEqualTo(finishedExcercise2);
        finishedExcercise1.setId(null);
        assertThat(finishedExcercise1).isNotEqualTo(finishedExcercise2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinishedExcerciseDTO.class);
        FinishedExcerciseDTO finishedExcerciseDTO1 = new FinishedExcerciseDTO();
        finishedExcerciseDTO1.setId(1L);
        FinishedExcerciseDTO finishedExcerciseDTO2 = new FinishedExcerciseDTO();
        assertThat(finishedExcerciseDTO1).isNotEqualTo(finishedExcerciseDTO2);
        finishedExcerciseDTO2.setId(finishedExcerciseDTO1.getId());
        assertThat(finishedExcerciseDTO1).isEqualTo(finishedExcerciseDTO2);
        finishedExcerciseDTO2.setId(2L);
        assertThat(finishedExcerciseDTO1).isNotEqualTo(finishedExcerciseDTO2);
        finishedExcerciseDTO1.setId(null);
        assertThat(finishedExcerciseDTO1).isNotEqualTo(finishedExcerciseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(finishedExcerciseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(finishedExcerciseMapper.fromId(null)).isNull();
    }
}
