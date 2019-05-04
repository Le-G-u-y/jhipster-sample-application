package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FinishedSession;
import io.github.jhipster.application.repository.FinishedSessionRepository;
import io.github.jhipster.application.service.FinishedSessionService;
import io.github.jhipster.application.service.dto.FinishedSessionDTO;
import io.github.jhipster.application.service.mapper.FinishedSessionMapper;
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
 * Integration tests for the {@Link FinishedSessionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FinishedSessionResourceIT {

    private static final Integer DEFAULT_MINUTES_TOTAL = 1;
    private static final Integer UPDATED_MINUTES_TOTAL = 2;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private FinishedSessionRepository finishedSessionRepository;

    @Autowired
    private FinishedSessionMapper finishedSessionMapper;

    @Autowired
    private FinishedSessionService finishedSessionService;

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

    private MockMvc restFinishedSessionMockMvc;

    private FinishedSession finishedSession;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FinishedSessionResource finishedSessionResource = new FinishedSessionResource(finishedSessionService);
        this.restFinishedSessionMockMvc = MockMvcBuilders.standaloneSetup(finishedSessionResource)
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
    public static FinishedSession createEntity(EntityManager em) {
        FinishedSession finishedSession = new FinishedSession()
            .minutesTotal(DEFAULT_MINUTES_TOTAL)
            .note(DEFAULT_NOTE)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return finishedSession;
    }

    @BeforeEach
    public void initTest() {
        finishedSession = createEntity(em);
    }

    @Test
    @Transactional
    public void createFinishedSession() throws Exception {
        int databaseSizeBeforeCreate = finishedSessionRepository.findAll().size();

        // Create the FinishedSession
        FinishedSessionDTO finishedSessionDTO = finishedSessionMapper.toDto(finishedSession);
        restFinishedSessionMockMvc.perform(post("/api/finished-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedSessionDTO)))
            .andExpect(status().isCreated());

        // Validate the FinishedSession in the database
        List<FinishedSession> finishedSessionList = finishedSessionRepository.findAll();
        assertThat(finishedSessionList).hasSize(databaseSizeBeforeCreate + 1);
        FinishedSession testFinishedSession = finishedSessionList.get(finishedSessionList.size() - 1);
        assertThat(testFinishedSession.getMinutesTotal()).isEqualTo(DEFAULT_MINUTES_TOTAL);
        assertThat(testFinishedSession.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testFinishedSession.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testFinishedSession.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void createFinishedSessionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = finishedSessionRepository.findAll().size();

        // Create the FinishedSession with an existing ID
        finishedSession.setId(1L);
        FinishedSessionDTO finishedSessionDTO = finishedSessionMapper.toDto(finishedSession);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFinishedSessionMockMvc.perform(post("/api/finished-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinishedSession in the database
        List<FinishedSession> finishedSessionList = finishedSessionRepository.findAll();
        assertThat(finishedSessionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = finishedSessionRepository.findAll().size();
        // set the field null
        finishedSession.setCreateDate(null);

        // Create the FinishedSession, which fails.
        FinishedSessionDTO finishedSessionDTO = finishedSessionMapper.toDto(finishedSession);

        restFinishedSessionMockMvc.perform(post("/api/finished-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedSessionDTO)))
            .andExpect(status().isBadRequest());

        List<FinishedSession> finishedSessionList = finishedSessionRepository.findAll();
        assertThat(finishedSessionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifyDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = finishedSessionRepository.findAll().size();
        // set the field null
        finishedSession.setModifyDate(null);

        // Create the FinishedSession, which fails.
        FinishedSessionDTO finishedSessionDTO = finishedSessionMapper.toDto(finishedSession);

        restFinishedSessionMockMvc.perform(post("/api/finished-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedSessionDTO)))
            .andExpect(status().isBadRequest());

        List<FinishedSession> finishedSessionList = finishedSessionRepository.findAll();
        assertThat(finishedSessionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFinishedSessions() throws Exception {
        // Initialize the database
        finishedSessionRepository.saveAndFlush(finishedSession);

        // Get all the finishedSessionList
        restFinishedSessionMockMvc.perform(get("/api/finished-sessions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(finishedSession.getId().intValue())))
            .andExpect(jsonPath("$.[*].minutesTotal").value(hasItem(DEFAULT_MINUTES_TOTAL)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getFinishedSession() throws Exception {
        // Initialize the database
        finishedSessionRepository.saveAndFlush(finishedSession);

        // Get the finishedSession
        restFinishedSessionMockMvc.perform(get("/api/finished-sessions/{id}", finishedSession.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(finishedSession.getId().intValue()))
            .andExpect(jsonPath("$.minutesTotal").value(DEFAULT_MINUTES_TOTAL))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFinishedSession() throws Exception {
        // Get the finishedSession
        restFinishedSessionMockMvc.perform(get("/api/finished-sessions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFinishedSession() throws Exception {
        // Initialize the database
        finishedSessionRepository.saveAndFlush(finishedSession);

        int databaseSizeBeforeUpdate = finishedSessionRepository.findAll().size();

        // Update the finishedSession
        FinishedSession updatedFinishedSession = finishedSessionRepository.findById(finishedSession.getId()).get();
        // Disconnect from session so that the updates on updatedFinishedSession are not directly saved in db
        em.detach(updatedFinishedSession);
        updatedFinishedSession
            .minutesTotal(UPDATED_MINUTES_TOTAL)
            .note(UPDATED_NOTE)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        FinishedSessionDTO finishedSessionDTO = finishedSessionMapper.toDto(updatedFinishedSession);

        restFinishedSessionMockMvc.perform(put("/api/finished-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedSessionDTO)))
            .andExpect(status().isOk());

        // Validate the FinishedSession in the database
        List<FinishedSession> finishedSessionList = finishedSessionRepository.findAll();
        assertThat(finishedSessionList).hasSize(databaseSizeBeforeUpdate);
        FinishedSession testFinishedSession = finishedSessionList.get(finishedSessionList.size() - 1);
        assertThat(testFinishedSession.getMinutesTotal()).isEqualTo(UPDATED_MINUTES_TOTAL);
        assertThat(testFinishedSession.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testFinishedSession.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testFinishedSession.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingFinishedSession() throws Exception {
        int databaseSizeBeforeUpdate = finishedSessionRepository.findAll().size();

        // Create the FinishedSession
        FinishedSessionDTO finishedSessionDTO = finishedSessionMapper.toDto(finishedSession);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFinishedSessionMockMvc.perform(put("/api/finished-sessions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(finishedSessionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FinishedSession in the database
        List<FinishedSession> finishedSessionList = finishedSessionRepository.findAll();
        assertThat(finishedSessionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFinishedSession() throws Exception {
        // Initialize the database
        finishedSessionRepository.saveAndFlush(finishedSession);

        int databaseSizeBeforeDelete = finishedSessionRepository.findAll().size();

        // Delete the finishedSession
        restFinishedSessionMockMvc.perform(delete("/api/finished-sessions/{id}", finishedSession.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FinishedSession> finishedSessionList = finishedSessionRepository.findAll();
        assertThat(finishedSessionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinishedSession.class);
        FinishedSession finishedSession1 = new FinishedSession();
        finishedSession1.setId(1L);
        FinishedSession finishedSession2 = new FinishedSession();
        finishedSession2.setId(finishedSession1.getId());
        assertThat(finishedSession1).isEqualTo(finishedSession2);
        finishedSession2.setId(2L);
        assertThat(finishedSession1).isNotEqualTo(finishedSession2);
        finishedSession1.setId(null);
        assertThat(finishedSession1).isNotEqualTo(finishedSession2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinishedSessionDTO.class);
        FinishedSessionDTO finishedSessionDTO1 = new FinishedSessionDTO();
        finishedSessionDTO1.setId(1L);
        FinishedSessionDTO finishedSessionDTO2 = new FinishedSessionDTO();
        assertThat(finishedSessionDTO1).isNotEqualTo(finishedSessionDTO2);
        finishedSessionDTO2.setId(finishedSessionDTO1.getId());
        assertThat(finishedSessionDTO1).isEqualTo(finishedSessionDTO2);
        finishedSessionDTO2.setId(2L);
        assertThat(finishedSessionDTO1).isNotEqualTo(finishedSessionDTO2);
        finishedSessionDTO1.setId(null);
        assertThat(finishedSessionDTO1).isNotEqualTo(finishedSessionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(finishedSessionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(finishedSessionMapper.fromId(null)).isNull();
    }
}
