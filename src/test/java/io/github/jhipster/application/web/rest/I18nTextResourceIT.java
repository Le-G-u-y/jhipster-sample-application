package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.I18nText;
import io.github.jhipster.application.repository.I18nTextRepository;
import io.github.jhipster.application.service.I18nTextService;
import io.github.jhipster.application.service.dto.I18nTextDTO;
import io.github.jhipster.application.service.mapper.I18nTextMapper;
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
 * Integration tests for the {@Link I18nTextResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class I18nTextResourceIT {

    private static final String DEFAULT_LOCALE = "AAAAAAAAAA";
    private static final String UPDATED_LOCALE = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_TEXT_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_TEXT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT_CONTENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private I18nTextRepository i18nTextRepository;

    @Autowired
    private I18nTextMapper i18nTextMapper;

    @Autowired
    private I18nTextService i18nTextService;

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

    private MockMvc restI18nTextMockMvc;

    private I18nText i18nText;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final I18nTextResource i18nTextResource = new I18nTextResource(i18nTextService);
        this.restI18nTextMockMvc = MockMvcBuilders.standaloneSetup(i18nTextResource)
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
    public static I18nText createEntity(EntityManager em) {
        I18nText i18nText = new I18nText()
            .locale(DEFAULT_LOCALE)
            .textKey(DEFAULT_TEXT_KEY)
            .textContent(DEFAULT_TEXT_CONTENT)
            .createDate(DEFAULT_CREATE_DATE)
            .modifyDate(DEFAULT_MODIFY_DATE);
        return i18nText;
    }

    @BeforeEach
    public void initTest() {
        i18nText = createEntity(em);
    }

    @Test
    @Transactional
    public void createI18nText() throws Exception {
        int databaseSizeBeforeCreate = i18nTextRepository.findAll().size();

        // Create the I18nText
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(i18nText);
        restI18nTextMockMvc.perform(post("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isCreated());

        // Validate the I18nText in the database
        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeCreate + 1);
        I18nText testI18nText = i18nTextList.get(i18nTextList.size() - 1);
        assertThat(testI18nText.getLocale()).isEqualTo(DEFAULT_LOCALE);
        assertThat(testI18nText.getTextKey()).isEqualTo(DEFAULT_TEXT_KEY);
        assertThat(testI18nText.getTextContent()).isEqualTo(DEFAULT_TEXT_CONTENT);
        assertThat(testI18nText.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testI18nText.getModifyDate()).isEqualTo(DEFAULT_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void createI18nTextWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = i18nTextRepository.findAll().size();

        // Create the I18nText with an existing ID
        i18nText.setId(1L);
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(i18nText);

        // An entity with an existing ID cannot be created, so this API call must fail
        restI18nTextMockMvc.perform(post("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isBadRequest());

        // Validate the I18nText in the database
        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLocaleIsRequired() throws Exception {
        int databaseSizeBeforeTest = i18nTextRepository.findAll().size();
        // set the field null
        i18nText.setLocale(null);

        // Create the I18nText, which fails.
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(i18nText);

        restI18nTextMockMvc.perform(post("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isBadRequest());

        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTextKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = i18nTextRepository.findAll().size();
        // set the field null
        i18nText.setTextKey(null);

        // Create the I18nText, which fails.
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(i18nText);

        restI18nTextMockMvc.perform(post("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isBadRequest());

        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = i18nTextRepository.findAll().size();
        // set the field null
        i18nText.setCreateDate(null);

        // Create the I18nText, which fails.
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(i18nText);

        restI18nTextMockMvc.perform(post("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isBadRequest());

        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifyDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = i18nTextRepository.findAll().size();
        // set the field null
        i18nText.setModifyDate(null);

        // Create the I18nText, which fails.
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(i18nText);

        restI18nTextMockMvc.perform(post("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isBadRequest());

        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllI18nTexts() throws Exception {
        // Initialize the database
        i18nTextRepository.saveAndFlush(i18nText);

        // Get all the i18nTextList
        restI18nTextMockMvc.perform(get("/api/i-18-n-texts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(i18nText.getId().intValue())))
            .andExpect(jsonPath("$.[*].locale").value(hasItem(DEFAULT_LOCALE.toString())))
            .andExpect(jsonPath("$.[*].textKey").value(hasItem(DEFAULT_TEXT_KEY.toString())))
            .andExpect(jsonPath("$.[*].textContent").value(hasItem(DEFAULT_TEXT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifyDate").value(hasItem(DEFAULT_MODIFY_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getI18nText() throws Exception {
        // Initialize the database
        i18nTextRepository.saveAndFlush(i18nText);

        // Get the i18nText
        restI18nTextMockMvc.perform(get("/api/i-18-n-texts/{id}", i18nText.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(i18nText.getId().intValue()))
            .andExpect(jsonPath("$.locale").value(DEFAULT_LOCALE.toString()))
            .andExpect(jsonPath("$.textKey").value(DEFAULT_TEXT_KEY.toString()))
            .andExpect(jsonPath("$.textContent").value(DEFAULT_TEXT_CONTENT.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.modifyDate").value(DEFAULT_MODIFY_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingI18nText() throws Exception {
        // Get the i18nText
        restI18nTextMockMvc.perform(get("/api/i-18-n-texts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateI18nText() throws Exception {
        // Initialize the database
        i18nTextRepository.saveAndFlush(i18nText);

        int databaseSizeBeforeUpdate = i18nTextRepository.findAll().size();

        // Update the i18nText
        I18nText updatedI18nText = i18nTextRepository.findById(i18nText.getId()).get();
        // Disconnect from session so that the updates on updatedI18nText are not directly saved in db
        em.detach(updatedI18nText);
        updatedI18nText
            .locale(UPDATED_LOCALE)
            .textKey(UPDATED_TEXT_KEY)
            .textContent(UPDATED_TEXT_CONTENT)
            .createDate(UPDATED_CREATE_DATE)
            .modifyDate(UPDATED_MODIFY_DATE);
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(updatedI18nText);

        restI18nTextMockMvc.perform(put("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isOk());

        // Validate the I18nText in the database
        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeUpdate);
        I18nText testI18nText = i18nTextList.get(i18nTextList.size() - 1);
        assertThat(testI18nText.getLocale()).isEqualTo(UPDATED_LOCALE);
        assertThat(testI18nText.getTextKey()).isEqualTo(UPDATED_TEXT_KEY);
        assertThat(testI18nText.getTextContent()).isEqualTo(UPDATED_TEXT_CONTENT);
        assertThat(testI18nText.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testI18nText.getModifyDate()).isEqualTo(UPDATED_MODIFY_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingI18nText() throws Exception {
        int databaseSizeBeforeUpdate = i18nTextRepository.findAll().size();

        // Create the I18nText
        I18nTextDTO i18nTextDTO = i18nTextMapper.toDto(i18nText);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restI18nTextMockMvc.perform(put("/api/i-18-n-texts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(i18nTextDTO)))
            .andExpect(status().isBadRequest());

        // Validate the I18nText in the database
        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteI18nText() throws Exception {
        // Initialize the database
        i18nTextRepository.saveAndFlush(i18nText);

        int databaseSizeBeforeDelete = i18nTextRepository.findAll().size();

        // Delete the i18nText
        restI18nTextMockMvc.perform(delete("/api/i-18-n-texts/{id}", i18nText.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<I18nText> i18nTextList = i18nTextRepository.findAll();
        assertThat(i18nTextList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(I18nText.class);
        I18nText i18nText1 = new I18nText();
        i18nText1.setId(1L);
        I18nText i18nText2 = new I18nText();
        i18nText2.setId(i18nText1.getId());
        assertThat(i18nText1).isEqualTo(i18nText2);
        i18nText2.setId(2L);
        assertThat(i18nText1).isNotEqualTo(i18nText2);
        i18nText1.setId(null);
        assertThat(i18nText1).isNotEqualTo(i18nText2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(I18nTextDTO.class);
        I18nTextDTO i18nTextDTO1 = new I18nTextDTO();
        i18nTextDTO1.setId(1L);
        I18nTextDTO i18nTextDTO2 = new I18nTextDTO();
        assertThat(i18nTextDTO1).isNotEqualTo(i18nTextDTO2);
        i18nTextDTO2.setId(i18nTextDTO1.getId());
        assertThat(i18nTextDTO1).isEqualTo(i18nTextDTO2);
        i18nTextDTO2.setId(2L);
        assertThat(i18nTextDTO1).isNotEqualTo(i18nTextDTO2);
        i18nTextDTO1.setId(null);
        assertThat(i18nTextDTO1).isNotEqualTo(i18nTextDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(i18nTextMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(i18nTextMapper.fromId(null)).isNull();
    }
}
