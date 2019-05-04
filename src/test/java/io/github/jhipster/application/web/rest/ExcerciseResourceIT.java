package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Excercise;
import io.github.jhipster.application.repository.ExcerciseRepository;
import io.github.jhipster.application.service.ExcerciseService;
import io.github.jhipster.application.service.dto.ExcerciseDTO;
import io.github.jhipster.application.service.mapper.ExcerciseMapper;
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

import io.github.jhipster.application.domain.enumeration.SkillType;
import io.github.jhipster.application.domain.enumeration.ExcerciseType;
/**
 * Integration tests for the {@Link ExcerciseResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ExcerciseResourceIT {

    private static final String DEFAULT_EXCERCISE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EXCERCISE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_DEFAULT_MINUTES = 9000;
    private static final Integer UPDATED_DEFAULT_MINUTES = 8999;

    private static final Integer DEFAULT_DEFAULT_TARGET_BPM = 1;
    private static final Integer UPDATED_DEFAULT_TARGET_BPM = 2;

    private static final SkillType DEFAULT_SKILL_TYPE = SkillType.SPEED;
    private static final SkillType UPDATED_SKILL_TYPE = SkillType.GROOVE;

    private static final ExcerciseType DEFAULT_EXCERCISE_TYPE = ExcerciseType.RUDIMENT;
    private static final ExcerciseType UPDATED_EXCERCISE_TYPE = ExcerciseType.TECHNIQUE;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ExcerciseRepository excerciseRepository;

    @Autowired
    private ExcerciseMapper excerciseMapper;

    @Autowired
    private ExcerciseService excerciseService;

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

    private MockMvc restExcerciseMockMvc;

    private Excercise excercise;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExcerciseResource excerciseResource = new ExcerciseResource(excerciseService);
        this.restExcerciseMockMvc = MockMvcBuilders.standaloneSetup(excerciseResource)
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
    public static Excercise createEntity(EntityManager em) {
        Excercise excercise = new Excercise()
            .excerciseName(DEFAULT_EXCERCISE_NAME)
            .description(DEFAULT_DESCRIPTION)
            .defaultMinutes(DEFAULT_DEFAULT_MINUTES)
            .defaultTargetBpm(DEFAULT_DEFAULT_TARGET_BPM)
            .skillType(DEFAULT_SKILL_TYPE)
            .excerciseType(DEFAULT_EXCERCISE_TYPE)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return excercise;
    }

    @BeforeEach
    public void initTest() {
        excercise = createEntity(em);
    }

    @Test
    @Transactional
    public void createExcercise() throws Exception {
        int databaseSizeBeforeCreate = excerciseRepository.findAll().size();

        // Create the Excercise
        ExcerciseDTO excerciseDTO = excerciseMapper.toDto(excercise);
        restExcerciseMockMvc.perform(post("/api/excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseDTO)))
            .andExpect(status().isCreated());

        // Validate the Excercise in the database
        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeCreate + 1);
        Excercise testExcercise = excerciseList.get(excerciseList.size() - 1);
        assertThat(testExcercise.getExcerciseName()).isEqualTo(DEFAULT_EXCERCISE_NAME);
        assertThat(testExcercise.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testExcercise.getDefaultMinutes()).isEqualTo(DEFAULT_DEFAULT_MINUTES);
        assertThat(testExcercise.getDefaultTargetBpm()).isEqualTo(DEFAULT_DEFAULT_TARGET_BPM);
        assertThat(testExcercise.getSkillType()).isEqualTo(DEFAULT_SKILL_TYPE);
        assertThat(testExcercise.getExcerciseType()).isEqualTo(DEFAULT_EXCERCISE_TYPE);
        assertThat(testExcercise.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testExcercise.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void createExcerciseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = excerciseRepository.findAll().size();

        // Create the Excercise with an existing ID
        excercise.setId(1L);
        ExcerciseDTO excerciseDTO = excerciseMapper.toDto(excercise);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExcerciseMockMvc.perform(post("/api/excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Excercise in the database
        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkExcerciseNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = excerciseRepository.findAll().size();
        // set the field null
        excercise.setExcerciseName(null);

        // Create the Excercise, which fails.
        ExcerciseDTO excerciseDTO = excerciseMapper.toDto(excercise);

        restExcerciseMockMvc.perform(post("/api/excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseDTO)))
            .andExpect(status().isBadRequest());

        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = excerciseRepository.findAll().size();
        // set the field null
        excercise.setCreateDate(null);

        // Create the Excercise, which fails.
        ExcerciseDTO excerciseDTO = excerciseMapper.toDto(excercise);

        restExcerciseMockMvc.perform(post("/api/excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseDTO)))
            .andExpect(status().isBadRequest());

        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifyDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = excerciseRepository.findAll().size();
        // set the field null
        excercise.setModifyDate(null);

        // Create the Excercise, which fails.
        ExcerciseDTO excerciseDTO = excerciseMapper.toDto(excercise);

        restExcerciseMockMvc.perform(post("/api/excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseDTO)))
            .andExpect(status().isBadRequest());

        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExcercises() throws Exception {
        // Initialize the database
        excerciseRepository.saveAndFlush(excercise);

        // Get all the excerciseList
        restExcerciseMockMvc.perform(get("/api/excercises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(excercise.getId().intValue())))
            .andExpect(jsonPath("$.[*].excerciseName").value(hasItem(DEFAULT_EXCERCISE_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].defaultMinutes").value(hasItem(DEFAULT_DEFAULT_MINUTES)))
            .andExpect(jsonPath("$.[*].defaultTargetBpm").value(hasItem(DEFAULT_DEFAULT_TARGET_BPM)))
            .andExpect(jsonPath("$.[*].skillType").value(hasItem(DEFAULT_SKILL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].excerciseType").value(hasItem(DEFAULT_EXCERCISE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getExcercise() throws Exception {
        // Initialize the database
        excerciseRepository.saveAndFlush(excercise);

        // Get the excercise
        restExcerciseMockMvc.perform(get("/api/excercises/{id}", excercise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(excercise.getId().intValue()))
            .andExpect(jsonPath("$.excerciseName").value(DEFAULT_EXCERCISE_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.defaultMinutes").value(DEFAULT_DEFAULT_MINUTES))
            .andExpect(jsonPath("$.defaultTargetBpm").value(DEFAULT_DEFAULT_TARGET_BPM))
            .andExpect(jsonPath("$.skillType").value(DEFAULT_SKILL_TYPE.toString()))
            .andExpect(jsonPath("$.excerciseType").value(DEFAULT_EXCERCISE_TYPE.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExcercise() throws Exception {
        // Get the excercise
        restExcerciseMockMvc.perform(get("/api/excercises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExcercise() throws Exception {
        // Initialize the database
        excerciseRepository.saveAndFlush(excercise);

        int databaseSizeBeforeUpdate = excerciseRepository.findAll().size();

        // Update the excercise
        Excercise updatedExcercise = excerciseRepository.findById(excercise.getId()).get();
        // Disconnect from session so that the updates on updatedExcercise are not directly saved in db
        em.detach(updatedExcercise);
        updatedExcercise
            .excerciseName(UPDATED_EXCERCISE_NAME)
            .description(UPDATED_DESCRIPTION)
            .defaultMinutes(UPDATED_DEFAULT_MINUTES)
            .defaultTargetBpm(UPDATED_DEFAULT_TARGET_BPM)
            .skillType(UPDATED_SKILL_TYPE)
            .excerciseType(UPDATED_EXCERCISE_TYPE)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        ExcerciseDTO excerciseDTO = excerciseMapper.toDto(updatedExcercise);

        restExcerciseMockMvc.perform(put("/api/excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseDTO)))
            .andExpect(status().isOk());

        // Validate the Excercise in the database
        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeUpdate);
        Excercise testExcercise = excerciseList.get(excerciseList.size() - 1);
        assertThat(testExcercise.getExcerciseName()).isEqualTo(UPDATED_EXCERCISE_NAME);
        assertThat(testExcercise.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testExcercise.getDefaultMinutes()).isEqualTo(UPDATED_DEFAULT_MINUTES);
        assertThat(testExcercise.getDefaultTargetBpm()).isEqualTo(UPDATED_DEFAULT_TARGET_BPM);
        assertThat(testExcercise.getSkillType()).isEqualTo(UPDATED_SKILL_TYPE);
        assertThat(testExcercise.getExcerciseType()).isEqualTo(UPDATED_EXCERCISE_TYPE);
        assertThat(testExcercise.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testExcercise.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingExcercise() throws Exception {
        int databaseSizeBeforeUpdate = excerciseRepository.findAll().size();

        // Create the Excercise
        ExcerciseDTO excerciseDTO = excerciseMapper.toDto(excercise);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExcerciseMockMvc.perform(put("/api/excercises")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Excercise in the database
        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExcercise() throws Exception {
        // Initialize the database
        excerciseRepository.saveAndFlush(excercise);

        int databaseSizeBeforeDelete = excerciseRepository.findAll().size();

        // Delete the excercise
        restExcerciseMockMvc.perform(delete("/api/excercises/{id}", excercise.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Excercise> excerciseList = excerciseRepository.findAll();
        assertThat(excerciseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Excercise.class);
        Excercise excercise1 = new Excercise();
        excercise1.setId(1L);
        Excercise excercise2 = new Excercise();
        excercise2.setId(excercise1.getId());
        assertThat(excercise1).isEqualTo(excercise2);
        excercise2.setId(2L);
        assertThat(excercise1).isNotEqualTo(excercise2);
        excercise1.setId(null);
        assertThat(excercise1).isNotEqualTo(excercise2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExcerciseDTO.class);
        ExcerciseDTO excerciseDTO1 = new ExcerciseDTO();
        excerciseDTO1.setId(1L);
        ExcerciseDTO excerciseDTO2 = new ExcerciseDTO();
        assertThat(excerciseDTO1).isNotEqualTo(excerciseDTO2);
        excerciseDTO2.setId(excerciseDTO1.getId());
        assertThat(excerciseDTO1).isEqualTo(excerciseDTO2);
        excerciseDTO2.setId(2L);
        assertThat(excerciseDTO1).isNotEqualTo(excerciseDTO2);
        excerciseDTO1.setId(null);
        assertThat(excerciseDTO1).isNotEqualTo(excerciseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(excerciseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(excerciseMapper.fromId(null)).isNull();
    }
}
