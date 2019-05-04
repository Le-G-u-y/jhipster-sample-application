package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ExcerciseConfig;
import io.github.jhipster.application.repository.ExcerciseConfigRepository;
import io.github.jhipster.application.service.ExcerciseConfigService;
import io.github.jhipster.application.service.dto.ExcerciseConfigDTO;
import io.github.jhipster.application.service.mapper.ExcerciseConfigMapper;
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
 * Integration tests for the {@Link ExcerciseConfigResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ExcerciseConfigResourceIT {

    private static final Integer DEFAULT_ACTUAL_BPM = 1;
    private static final Integer UPDATED_ACTUAL_BPM = 2;

    private static final Integer DEFAULT_TARGET_BPM = 1;
    private static final Integer UPDATED_TARGET_BPM = 2;

    private static final Integer DEFAULT_MINUTES = 1;
    private static final Integer UPDATED_MINUTES = 2;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private ExcerciseConfigRepository excerciseConfigRepository;

    @Autowired
    private ExcerciseConfigMapper excerciseConfigMapper;

    @Autowired
    private ExcerciseConfigService excerciseConfigService;

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

    private MockMvc restExcerciseConfigMockMvc;

    private ExcerciseConfig excerciseConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ExcerciseConfigResource excerciseConfigResource = new ExcerciseConfigResource(excerciseConfigService);
        this.restExcerciseConfigMockMvc = MockMvcBuilders.standaloneSetup(excerciseConfigResource)
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
    public static ExcerciseConfig createEntity(EntityManager em) {
        ExcerciseConfig excerciseConfig = new ExcerciseConfig()
            .actualBpm(DEFAULT_ACTUAL_BPM)
            .targetBpm(DEFAULT_TARGET_BPM)
            .minutes(DEFAULT_MINUTES)
            .note(DEFAULT_NOTE)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return excerciseConfig;
    }

    @BeforeEach
    public void initTest() {
        excerciseConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createExcerciseConfig() throws Exception {
        int databaseSizeBeforeCreate = excerciseConfigRepository.findAll().size();

        // Create the ExcerciseConfig
        ExcerciseConfigDTO excerciseConfigDTO = excerciseConfigMapper.toDto(excerciseConfig);
        restExcerciseConfigMockMvc.perform(post("/api/excercise-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseConfigDTO)))
            .andExpect(status().isCreated());

        // Validate the ExcerciseConfig in the database
        List<ExcerciseConfig> excerciseConfigList = excerciseConfigRepository.findAll();
        assertThat(excerciseConfigList).hasSize(databaseSizeBeforeCreate + 1);
        ExcerciseConfig testExcerciseConfig = excerciseConfigList.get(excerciseConfigList.size() - 1);
        assertThat(testExcerciseConfig.getActualBpm()).isEqualTo(DEFAULT_ACTUAL_BPM);
        assertThat(testExcerciseConfig.getTargetBpm()).isEqualTo(DEFAULT_TARGET_BPM);
        assertThat(testExcerciseConfig.getMinutes()).isEqualTo(DEFAULT_MINUTES);
        assertThat(testExcerciseConfig.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testExcerciseConfig.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testExcerciseConfig.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void createExcerciseConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = excerciseConfigRepository.findAll().size();

        // Create the ExcerciseConfig with an existing ID
        excerciseConfig.setId(1L);
        ExcerciseConfigDTO excerciseConfigDTO = excerciseConfigMapper.toDto(excerciseConfig);

        // An entity with an existing ID cannot be created, so this API call must fail
        restExcerciseConfigMockMvc.perform(post("/api/excercise-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExcerciseConfig in the database
        List<ExcerciseConfig> excerciseConfigList = excerciseConfigRepository.findAll();
        assertThat(excerciseConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = excerciseConfigRepository.findAll().size();
        // set the field null
        excerciseConfig.setCreateDate(null);

        // Create the ExcerciseConfig, which fails.
        ExcerciseConfigDTO excerciseConfigDTO = excerciseConfigMapper.toDto(excerciseConfig);

        restExcerciseConfigMockMvc.perform(post("/api/excercise-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseConfigDTO)))
            .andExpect(status().isBadRequest());

        List<ExcerciseConfig> excerciseConfigList = excerciseConfigRepository.findAll();
        assertThat(excerciseConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifyDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = excerciseConfigRepository.findAll().size();
        // set the field null
        excerciseConfig.setModifyDate(null);

        // Create the ExcerciseConfig, which fails.
        ExcerciseConfigDTO excerciseConfigDTO = excerciseConfigMapper.toDto(excerciseConfig);

        restExcerciseConfigMockMvc.perform(post("/api/excercise-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseConfigDTO)))
            .andExpect(status().isBadRequest());

        List<ExcerciseConfig> excerciseConfigList = excerciseConfigRepository.findAll();
        assertThat(excerciseConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllExcerciseConfigs() throws Exception {
        // Initialize the database
        excerciseConfigRepository.saveAndFlush(excerciseConfig);

        // Get all the excerciseConfigList
        restExcerciseConfigMockMvc.perform(get("/api/excercise-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(excerciseConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].actualBpm").value(hasItem(DEFAULT_ACTUAL_BPM)))
            .andExpect(jsonPath("$.[*].targetBpm").value(hasItem(DEFAULT_TARGET_BPM)))
            .andExpect(jsonPath("$.[*].minutes").value(hasItem(DEFAULT_MINUTES)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getExcerciseConfig() throws Exception {
        // Initialize the database
        excerciseConfigRepository.saveAndFlush(excerciseConfig);

        // Get the excerciseConfig
        restExcerciseConfigMockMvc.perform(get("/api/excercise-configs/{id}", excerciseConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(excerciseConfig.getId().intValue()))
            .andExpect(jsonPath("$.actualBpm").value(DEFAULT_ACTUAL_BPM))
            .andExpect(jsonPath("$.targetBpm").value(DEFAULT_TARGET_BPM))
            .andExpect(jsonPath("$.minutes").value(DEFAULT_MINUTES))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingExcerciseConfig() throws Exception {
        // Get the excerciseConfig
        restExcerciseConfigMockMvc.perform(get("/api/excercise-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateExcerciseConfig() throws Exception {
        // Initialize the database
        excerciseConfigRepository.saveAndFlush(excerciseConfig);

        int databaseSizeBeforeUpdate = excerciseConfigRepository.findAll().size();

        // Update the excerciseConfig
        ExcerciseConfig updatedExcerciseConfig = excerciseConfigRepository.findById(excerciseConfig.getId()).get();
        // Disconnect from session so that the updates on updatedExcerciseConfig are not directly saved in db
        em.detach(updatedExcerciseConfig);
        updatedExcerciseConfig
            .actualBpm(UPDATED_ACTUAL_BPM)
            .targetBpm(UPDATED_TARGET_BPM)
            .minutes(UPDATED_MINUTES)
            .note(UPDATED_NOTE)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        ExcerciseConfigDTO excerciseConfigDTO = excerciseConfigMapper.toDto(updatedExcerciseConfig);

        restExcerciseConfigMockMvc.perform(put("/api/excercise-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseConfigDTO)))
            .andExpect(status().isOk());

        // Validate the ExcerciseConfig in the database
        List<ExcerciseConfig> excerciseConfigList = excerciseConfigRepository.findAll();
        assertThat(excerciseConfigList).hasSize(databaseSizeBeforeUpdate);
        ExcerciseConfig testExcerciseConfig = excerciseConfigList.get(excerciseConfigList.size() - 1);
        assertThat(testExcerciseConfig.getActualBpm()).isEqualTo(UPDATED_ACTUAL_BPM);
        assertThat(testExcerciseConfig.getTargetBpm()).isEqualTo(UPDATED_TARGET_BPM);
        assertThat(testExcerciseConfig.getMinutes()).isEqualTo(UPDATED_MINUTES);
        assertThat(testExcerciseConfig.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testExcerciseConfig.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testExcerciseConfig.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingExcerciseConfig() throws Exception {
        int databaseSizeBeforeUpdate = excerciseConfigRepository.findAll().size();

        // Create the ExcerciseConfig
        ExcerciseConfigDTO excerciseConfigDTO = excerciseConfigMapper.toDto(excerciseConfig);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExcerciseConfigMockMvc.perform(put("/api/excercise-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(excerciseConfigDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExcerciseConfig in the database
        List<ExcerciseConfig> excerciseConfigList = excerciseConfigRepository.findAll();
        assertThat(excerciseConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteExcerciseConfig() throws Exception {
        // Initialize the database
        excerciseConfigRepository.saveAndFlush(excerciseConfig);

        int databaseSizeBeforeDelete = excerciseConfigRepository.findAll().size();

        // Delete the excerciseConfig
        restExcerciseConfigMockMvc.perform(delete("/api/excercise-configs/{id}", excerciseConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ExcerciseConfig> excerciseConfigList = excerciseConfigRepository.findAll();
        assertThat(excerciseConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExcerciseConfig.class);
        ExcerciseConfig excerciseConfig1 = new ExcerciseConfig();
        excerciseConfig1.setId(1L);
        ExcerciseConfig excerciseConfig2 = new ExcerciseConfig();
        excerciseConfig2.setId(excerciseConfig1.getId());
        assertThat(excerciseConfig1).isEqualTo(excerciseConfig2);
        excerciseConfig2.setId(2L);
        assertThat(excerciseConfig1).isNotEqualTo(excerciseConfig2);
        excerciseConfig1.setId(null);
        assertThat(excerciseConfig1).isNotEqualTo(excerciseConfig2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExcerciseConfigDTO.class);
        ExcerciseConfigDTO excerciseConfigDTO1 = new ExcerciseConfigDTO();
        excerciseConfigDTO1.setId(1L);
        ExcerciseConfigDTO excerciseConfigDTO2 = new ExcerciseConfigDTO();
        assertThat(excerciseConfigDTO1).isNotEqualTo(excerciseConfigDTO2);
        excerciseConfigDTO2.setId(excerciseConfigDTO1.getId());
        assertThat(excerciseConfigDTO1).isEqualTo(excerciseConfigDTO2);
        excerciseConfigDTO2.setId(2L);
        assertThat(excerciseConfigDTO1).isNotEqualTo(excerciseConfigDTO2);
        excerciseConfigDTO1.setId(null);
        assertThat(excerciseConfigDTO1).isNotEqualTo(excerciseConfigDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(excerciseConfigMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(excerciseConfigMapper.fromId(null)).isNull();
    }
}
