package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Drummer;
import io.github.jhipster.application.repository.DrummerRepository;
import io.github.jhipster.application.service.DrummerService;
import io.github.jhipster.application.service.dto.DrummerDTO;
import io.github.jhipster.application.service.mapper.DrummerMapper;
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
 * Integration tests for the {@Link DrummerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class DrummerResourceIT {

    private static final String DEFAULT_DRUMMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DRUMMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DrummerRepository drummerRepository;

    @Autowired
    private DrummerMapper drummerMapper;

    @Autowired
    private DrummerService drummerService;

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

    private MockMvc restDrummerMockMvc;

    private Drummer drummer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DrummerResource drummerResource = new DrummerResource(drummerService);
        this.restDrummerMockMvc = MockMvcBuilders.standaloneSetup(drummerResource)
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
    public static Drummer createEntity(EntityManager em) {
        Drummer drummer = new Drummer()
            .drummerName(DEFAULT_DRUMMER_NAME)
            .description(DEFAULT_DESCRIPTION)
            .email(DEFAULT_EMAIL)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return drummer;
    }

    @BeforeEach
    public void initTest() {
        drummer = createEntity(em);
    }

    @Test
    @Transactional
    public void createDrummer() throws Exception {
        int databaseSizeBeforeCreate = drummerRepository.findAll().size();

        // Create the Drummer
        DrummerDTO drummerDTO = drummerMapper.toDto(drummer);
        restDrummerMockMvc.perform(post("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isCreated());

        // Validate the Drummer in the database
        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeCreate + 1);
        Drummer testDrummer = drummerList.get(drummerList.size() - 1);
        assertThat(testDrummer.getDrummerName()).isEqualTo(DEFAULT_DRUMMER_NAME);
        assertThat(testDrummer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDrummer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDrummer.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testDrummer.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void createDrummerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = drummerRepository.findAll().size();

        // Create the Drummer with an existing ID
        drummer.setId(1L);
        DrummerDTO drummerDTO = drummerMapper.toDto(drummer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDrummerMockMvc.perform(post("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Drummer in the database
        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDrummerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = drummerRepository.findAll().size();
        // set the field null
        drummer.setDrummerName(null);

        // Create the Drummer, which fails.
        DrummerDTO drummerDTO = drummerMapper.toDto(drummer);

        restDrummerMockMvc.perform(post("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isBadRequest());

        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = drummerRepository.findAll().size();
        // set the field null
        drummer.setEmail(null);

        // Create the Drummer, which fails.
        DrummerDTO drummerDTO = drummerMapper.toDto(drummer);

        restDrummerMockMvc.perform(post("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isBadRequest());

        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = drummerRepository.findAll().size();
        // set the field null
        drummer.setCreateDate(null);

        // Create the Drummer, which fails.
        DrummerDTO drummerDTO = drummerMapper.toDto(drummer);

        restDrummerMockMvc.perform(post("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isBadRequest());

        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifyDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = drummerRepository.findAll().size();
        // set the field null
        drummer.setModifyDate(null);

        // Create the Drummer, which fails.
        DrummerDTO drummerDTO = drummerMapper.toDto(drummer);

        restDrummerMockMvc.perform(post("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isBadRequest());

        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDrummers() throws Exception {
        // Initialize the database
        drummerRepository.saveAndFlush(drummer);

        // Get all the drummerList
        restDrummerMockMvc.perform(get("/api/drummers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(drummer.getId().intValue())))
            .andExpect(jsonPath("$.[*].drummerName").value(hasItem(DEFAULT_DRUMMER_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getDrummer() throws Exception {
        // Initialize the database
        drummerRepository.saveAndFlush(drummer);

        // Get the drummer
        restDrummerMockMvc.perform(get("/api/drummers/{id}", drummer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(drummer.getId().intValue()))
            .andExpect(jsonPath("$.drummerName").value(DEFAULT_DRUMMER_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDrummer() throws Exception {
        // Get the drummer
        restDrummerMockMvc.perform(get("/api/drummers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDrummer() throws Exception {
        // Initialize the database
        drummerRepository.saveAndFlush(drummer);

        int databaseSizeBeforeUpdate = drummerRepository.findAll().size();

        // Update the drummer
        Drummer updatedDrummer = drummerRepository.findById(drummer.getId()).get();
        // Disconnect from session so that the updates on updatedDrummer are not directly saved in db
        em.detach(updatedDrummer);
        updatedDrummer
            .drummerName(UPDATED_DRUMMER_NAME)
            .description(UPDATED_DESCRIPTION)
            .email(UPDATED_EMAIL)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        DrummerDTO drummerDTO = drummerMapper.toDto(updatedDrummer);

        restDrummerMockMvc.perform(put("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isOk());

        // Validate the Drummer in the database
        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeUpdate);
        Drummer testDrummer = drummerList.get(drummerList.size() - 1);
        assertThat(testDrummer.getDrummerName()).isEqualTo(UPDATED_DRUMMER_NAME);
        assertThat(testDrummer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDrummer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDrummer.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testDrummer.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDrummer() throws Exception {
        int databaseSizeBeforeUpdate = drummerRepository.findAll().size();

        // Create the Drummer
        DrummerDTO drummerDTO = drummerMapper.toDto(drummer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDrummerMockMvc.perform(put("/api/drummers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Drummer in the database
        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDrummer() throws Exception {
        // Initialize the database
        drummerRepository.saveAndFlush(drummer);

        int databaseSizeBeforeDelete = drummerRepository.findAll().size();

        // Delete the drummer
        restDrummerMockMvc.perform(delete("/api/drummers/{id}", drummer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Drummer> drummerList = drummerRepository.findAll();
        assertThat(drummerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Drummer.class);
        Drummer drummer1 = new Drummer();
        drummer1.setId(1L);
        Drummer drummer2 = new Drummer();
        drummer2.setId(drummer1.getId());
        assertThat(drummer1).isEqualTo(drummer2);
        drummer2.setId(2L);
        assertThat(drummer1).isNotEqualTo(drummer2);
        drummer1.setId(null);
        assertThat(drummer1).isNotEqualTo(drummer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DrummerDTO.class);
        DrummerDTO drummerDTO1 = new DrummerDTO();
        drummerDTO1.setId(1L);
        DrummerDTO drummerDTO2 = new DrummerDTO();
        assertThat(drummerDTO1).isNotEqualTo(drummerDTO2);
        drummerDTO2.setId(drummerDTO1.getId());
        assertThat(drummerDTO1).isEqualTo(drummerDTO2);
        drummerDTO2.setId(2L);
        assertThat(drummerDTO1).isNotEqualTo(drummerDTO2);
        drummerDTO1.setId(null);
        assertThat(drummerDTO1).isNotEqualTo(drummerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(drummerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(drummerMapper.fromId(null)).isNull();
    }
}
