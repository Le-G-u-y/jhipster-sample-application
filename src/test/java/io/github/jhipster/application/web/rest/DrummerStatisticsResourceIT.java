package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.DrummerStatistics;
import io.github.jhipster.application.repository.DrummerStatisticsRepository;
import io.github.jhipster.application.service.DrummerStatisticsService;
import io.github.jhipster.application.service.dto.DrummerStatisticsDTO;
import io.github.jhipster.application.service.mapper.DrummerStatisticsMapper;
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
 * Integration tests for the {@Link DrummerStatisticsResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class DrummerStatisticsResourceIT {

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_SPEED = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_SPEED = 1;

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_GROOVE = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_GROOVE = 1;

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_CREATIVITY = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_CREATIVITY = 1;

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_ADAPTABILITY = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_ADAPTABILITY = 1;

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_DYNAMICS = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_DYNAMICS = 1;

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_INDEPENDENCE = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_INDEPENDENCE = 1;

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE = 1;

    private static final Integer DEFAULT_SELF_ASSESSED_LEVEL_READING_MUSIC = 0;
    private static final Integer UPDATED_SELF_ASSESSED_LEVEL_READING_MUSIC = 1;

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private DrummerStatisticsRepository drummerStatisticsRepository;

    @Autowired
    private DrummerStatisticsMapper drummerStatisticsMapper;

    @Autowired
    private DrummerStatisticsService drummerStatisticsService;

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

    private MockMvc restDrummerStatisticsMockMvc;

    private DrummerStatistics drummerStatistics;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DrummerStatisticsResource drummerStatisticsResource = new DrummerStatisticsResource(drummerStatisticsService);
        this.restDrummerStatisticsMockMvc = MockMvcBuilders.standaloneSetup(drummerStatisticsResource)
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
    public static DrummerStatistics createEntity(EntityManager em) {
        DrummerStatistics drummerStatistics = new DrummerStatistics()
            .selfAssessedLevelSpeed(DEFAULT_SELF_ASSESSED_LEVEL_SPEED)
            .selfAssessedLevelGroove(DEFAULT_SELF_ASSESSED_LEVEL_GROOVE)
            .selfAssessedLevelCreativity(DEFAULT_SELF_ASSESSED_LEVEL_CREATIVITY)
            .selfAssessedLevelAdaptability(DEFAULT_SELF_ASSESSED_LEVEL_ADAPTABILITY)
            .selfAssessedLevelDynamics(DEFAULT_SELF_ASSESSED_LEVEL_DYNAMICS)
            .selfAssessedLevelIndependence(DEFAULT_SELF_ASSESSED_LEVEL_INDEPENDENCE)
            .selfAssessedLevelLivePerformance(DEFAULT_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE)
            .selfAssessedLevelReadingMusic(DEFAULT_SELF_ASSESSED_LEVEL_READING_MUSIC)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return drummerStatistics;
    }

    @BeforeEach
    public void initTest() {
        drummerStatistics = createEntity(em);
    }

    @Test
    @Transactional
    public void createDrummerStatistics() throws Exception {
        int databaseSizeBeforeCreate = drummerStatisticsRepository.findAll().size();

        // Create the DrummerStatistics
        DrummerStatisticsDTO drummerStatisticsDTO = drummerStatisticsMapper.toDto(drummerStatistics);
        restDrummerStatisticsMockMvc.perform(post("/api/drummer-statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerStatisticsDTO)))
            .andExpect(status().isCreated());

        // Validate the DrummerStatistics in the database
        List<DrummerStatistics> drummerStatisticsList = drummerStatisticsRepository.findAll();
        assertThat(drummerStatisticsList).hasSize(databaseSizeBeforeCreate + 1);
        DrummerStatistics testDrummerStatistics = drummerStatisticsList.get(drummerStatisticsList.size() - 1);
        assertThat(testDrummerStatistics.getSelfAssessedLevelSpeed()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_SPEED);
        assertThat(testDrummerStatistics.getSelfAssessedLevelGroove()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_GROOVE);
        assertThat(testDrummerStatistics.getSelfAssessedLevelCreativity()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_CREATIVITY);
        assertThat(testDrummerStatistics.getSelfAssessedLevelAdaptability()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_ADAPTABILITY);
        assertThat(testDrummerStatistics.getSelfAssessedLevelDynamics()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_DYNAMICS);
        assertThat(testDrummerStatistics.getSelfAssessedLevelIndependence()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_INDEPENDENCE);
        assertThat(testDrummerStatistics.getSelfAssessedLevelLivePerformance()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE);
        assertThat(testDrummerStatistics.getSelfAssessedLevelReadingMusic()).isEqualTo(DEFAULT_SELF_ASSESSED_LEVEL_READING_MUSIC);
        assertThat(testDrummerStatistics.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testDrummerStatistics.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void createDrummerStatisticsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = drummerStatisticsRepository.findAll().size();

        // Create the DrummerStatistics with an existing ID
        drummerStatistics.setId(1L);
        DrummerStatisticsDTO drummerStatisticsDTO = drummerStatisticsMapper.toDto(drummerStatistics);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDrummerStatisticsMockMvc.perform(post("/api/drummer-statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerStatisticsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DrummerStatistics in the database
        List<DrummerStatistics> drummerStatisticsList = drummerStatisticsRepository.findAll();
        assertThat(drummerStatisticsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = drummerStatisticsRepository.findAll().size();
        // set the field null
        drummerStatistics.setCreateDate(null);

        // Create the DrummerStatistics, which fails.
        DrummerStatisticsDTO drummerStatisticsDTO = drummerStatisticsMapper.toDto(drummerStatistics);

        restDrummerStatisticsMockMvc.perform(post("/api/drummer-statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerStatisticsDTO)))
            .andExpect(status().isBadRequest());

        List<DrummerStatistics> drummerStatisticsList = drummerStatisticsRepository.findAll();
        assertThat(drummerStatisticsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifyDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = drummerStatisticsRepository.findAll().size();
        // set the field null
        drummerStatistics.setModifyDate(null);

        // Create the DrummerStatistics, which fails.
        DrummerStatisticsDTO drummerStatisticsDTO = drummerStatisticsMapper.toDto(drummerStatistics);

        restDrummerStatisticsMockMvc.perform(post("/api/drummer-statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerStatisticsDTO)))
            .andExpect(status().isBadRequest());

        List<DrummerStatistics> drummerStatisticsList = drummerStatisticsRepository.findAll();
        assertThat(drummerStatisticsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDrummerStatistics() throws Exception {
        // Initialize the database
        drummerStatisticsRepository.saveAndFlush(drummerStatistics);

        // Get all the drummerStatisticsList
        restDrummerStatisticsMockMvc.perform(get("/api/drummer-statistics?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(drummerStatistics.getId().intValue())))
            .andExpect(jsonPath("$.[*].selfAssessedLevelSpeed").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_SPEED)))
            .andExpect(jsonPath("$.[*].selfAssessedLevelGroove").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_GROOVE)))
            .andExpect(jsonPath("$.[*].selfAssessedLevelCreativity").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_CREATIVITY)))
            .andExpect(jsonPath("$.[*].selfAssessedLevelAdaptability").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_ADAPTABILITY)))
            .andExpect(jsonPath("$.[*].selfAssessedLevelDynamics").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_DYNAMICS)))
            .andExpect(jsonPath("$.[*].selfAssessedLevelIndependence").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_INDEPENDENCE)))
            .andExpect(jsonPath("$.[*].selfAssessedLevelLivePerformance").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE)))
            .andExpect(jsonPath("$.[*].selfAssessedLevelReadingMusic").value(hasItem(DEFAULT_SELF_ASSESSED_LEVEL_READING_MUSIC)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getDrummerStatistics() throws Exception {
        // Initialize the database
        drummerStatisticsRepository.saveAndFlush(drummerStatistics);

        // Get the drummerStatistics
        restDrummerStatisticsMockMvc.perform(get("/api/drummer-statistics/{id}", drummerStatistics.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(drummerStatistics.getId().intValue()))
            .andExpect(jsonPath("$.selfAssessedLevelSpeed").value(DEFAULT_SELF_ASSESSED_LEVEL_SPEED))
            .andExpect(jsonPath("$.selfAssessedLevelGroove").value(DEFAULT_SELF_ASSESSED_LEVEL_GROOVE))
            .andExpect(jsonPath("$.selfAssessedLevelCreativity").value(DEFAULT_SELF_ASSESSED_LEVEL_CREATIVITY))
            .andExpect(jsonPath("$.selfAssessedLevelAdaptability").value(DEFAULT_SELF_ASSESSED_LEVEL_ADAPTABILITY))
            .andExpect(jsonPath("$.selfAssessedLevelDynamics").value(DEFAULT_SELF_ASSESSED_LEVEL_DYNAMICS))
            .andExpect(jsonPath("$.selfAssessedLevelIndependence").value(DEFAULT_SELF_ASSESSED_LEVEL_INDEPENDENCE))
            .andExpect(jsonPath("$.selfAssessedLevelLivePerformance").value(DEFAULT_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE))
            .andExpect(jsonPath("$.selfAssessedLevelReadingMusic").value(DEFAULT_SELF_ASSESSED_LEVEL_READING_MUSIC))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDrummerStatistics() throws Exception {
        // Get the drummerStatistics
        restDrummerStatisticsMockMvc.perform(get("/api/drummer-statistics/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDrummerStatistics() throws Exception {
        // Initialize the database
        drummerStatisticsRepository.saveAndFlush(drummerStatistics);

        int databaseSizeBeforeUpdate = drummerStatisticsRepository.findAll().size();

        // Update the drummerStatistics
        DrummerStatistics updatedDrummerStatistics = drummerStatisticsRepository.findById(drummerStatistics.getId()).get();
        // Disconnect from session so that the updates on updatedDrummerStatistics are not directly saved in db
        em.detach(updatedDrummerStatistics);
        updatedDrummerStatistics
            .selfAssessedLevelSpeed(UPDATED_SELF_ASSESSED_LEVEL_SPEED)
            .selfAssessedLevelGroove(UPDATED_SELF_ASSESSED_LEVEL_GROOVE)
            .selfAssessedLevelCreativity(UPDATED_SELF_ASSESSED_LEVEL_CREATIVITY)
            .selfAssessedLevelAdaptability(UPDATED_SELF_ASSESSED_LEVEL_ADAPTABILITY)
            .selfAssessedLevelDynamics(UPDATED_SELF_ASSESSED_LEVEL_DYNAMICS)
            .selfAssessedLevelIndependence(UPDATED_SELF_ASSESSED_LEVEL_INDEPENDENCE)
            .selfAssessedLevelLivePerformance(UPDATED_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE)
            .selfAssessedLevelReadingMusic(UPDATED_SELF_ASSESSED_LEVEL_READING_MUSIC)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        DrummerStatisticsDTO drummerStatisticsDTO = drummerStatisticsMapper.toDto(updatedDrummerStatistics);

        restDrummerStatisticsMockMvc.perform(put("/api/drummer-statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerStatisticsDTO)))
            .andExpect(status().isOk());

        // Validate the DrummerStatistics in the database
        List<DrummerStatistics> drummerStatisticsList = drummerStatisticsRepository.findAll();
        assertThat(drummerStatisticsList).hasSize(databaseSizeBeforeUpdate);
        DrummerStatistics testDrummerStatistics = drummerStatisticsList.get(drummerStatisticsList.size() - 1);
        assertThat(testDrummerStatistics.getSelfAssessedLevelSpeed()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_SPEED);
        assertThat(testDrummerStatistics.getSelfAssessedLevelGroove()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_GROOVE);
        assertThat(testDrummerStatistics.getSelfAssessedLevelCreativity()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_CREATIVITY);
        assertThat(testDrummerStatistics.getSelfAssessedLevelAdaptability()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_ADAPTABILITY);
        assertThat(testDrummerStatistics.getSelfAssessedLevelDynamics()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_DYNAMICS);
        assertThat(testDrummerStatistics.getSelfAssessedLevelIndependence()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_INDEPENDENCE);
        assertThat(testDrummerStatistics.getSelfAssessedLevelLivePerformance()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_LIVE_PERFORMANCE);
        assertThat(testDrummerStatistics.getSelfAssessedLevelReadingMusic()).isEqualTo(UPDATED_SELF_ASSESSED_LEVEL_READING_MUSIC);
        assertThat(testDrummerStatistics.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testDrummerStatistics.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingDrummerStatistics() throws Exception {
        int databaseSizeBeforeUpdate = drummerStatisticsRepository.findAll().size();

        // Create the DrummerStatistics
        DrummerStatisticsDTO drummerStatisticsDTO = drummerStatisticsMapper.toDto(drummerStatistics);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDrummerStatisticsMockMvc.perform(put("/api/drummer-statistics")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(drummerStatisticsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DrummerStatistics in the database
        List<DrummerStatistics> drummerStatisticsList = drummerStatisticsRepository.findAll();
        assertThat(drummerStatisticsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDrummerStatistics() throws Exception {
        // Initialize the database
        drummerStatisticsRepository.saveAndFlush(drummerStatistics);

        int databaseSizeBeforeDelete = drummerStatisticsRepository.findAll().size();

        // Delete the drummerStatistics
        restDrummerStatisticsMockMvc.perform(delete("/api/drummer-statistics/{id}", drummerStatistics.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<DrummerStatistics> drummerStatisticsList = drummerStatisticsRepository.findAll();
        assertThat(drummerStatisticsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DrummerStatistics.class);
        DrummerStatistics drummerStatistics1 = new DrummerStatistics();
        drummerStatistics1.setId(1L);
        DrummerStatistics drummerStatistics2 = new DrummerStatistics();
        drummerStatistics2.setId(drummerStatistics1.getId());
        assertThat(drummerStatistics1).isEqualTo(drummerStatistics2);
        drummerStatistics2.setId(2L);
        assertThat(drummerStatistics1).isNotEqualTo(drummerStatistics2);
        drummerStatistics1.setId(null);
        assertThat(drummerStatistics1).isNotEqualTo(drummerStatistics2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DrummerStatisticsDTO.class);
        DrummerStatisticsDTO drummerStatisticsDTO1 = new DrummerStatisticsDTO();
        drummerStatisticsDTO1.setId(1L);
        DrummerStatisticsDTO drummerStatisticsDTO2 = new DrummerStatisticsDTO();
        assertThat(drummerStatisticsDTO1).isNotEqualTo(drummerStatisticsDTO2);
        drummerStatisticsDTO2.setId(drummerStatisticsDTO1.getId());
        assertThat(drummerStatisticsDTO1).isEqualTo(drummerStatisticsDTO2);
        drummerStatisticsDTO2.setId(2L);
        assertThat(drummerStatisticsDTO1).isNotEqualTo(drummerStatisticsDTO2);
        drummerStatisticsDTO1.setId(null);
        assertThat(drummerStatisticsDTO1).isNotEqualTo(drummerStatisticsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(drummerStatisticsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(drummerStatisticsMapper.fromId(null)).isNull();
    }
}
