package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.HamrofoodmanduApp;

import io.github.jhipster.application.domain.OpeningHour;
import io.github.jhipster.application.repository.OpeningHourRepository;
import io.github.jhipster.application.service.OpeningHourService;
import io.github.jhipster.application.service.dto.OpeningHourDTO;
import io.github.jhipster.application.service.mapper.OpeningHourMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OpeningHourResource REST controller.
 *
 * @see OpeningHourResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HamrofoodmanduApp.class)
public class OpeningHourResourceIntTest {

    private static final String DEFAULT_DAY = "AAAAAAAAAA";
    private static final String UPDATED_DAY = "BBBBBBBBBB";

    private static final String DEFAULT_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TIME = "BBBBBBBBBB";

    @Autowired
    private OpeningHourRepository openingHourRepository;


    @Autowired
    private OpeningHourMapper openingHourMapper;
    

    @Autowired
    private OpeningHourService openingHourService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOpeningHourMockMvc;

    private OpeningHour openingHour;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OpeningHourResource openingHourResource = new OpeningHourResource(openingHourService);
        this.restOpeningHourMockMvc = MockMvcBuilders.standaloneSetup(openingHourResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpeningHour createEntity(EntityManager em) {
        OpeningHour openingHour = new OpeningHour()
            .day(DEFAULT_DAY)
            .time(DEFAULT_TIME);
        return openingHour;
    }

    @Before
    public void initTest() {
        openingHour = createEntity(em);
    }

    @Test
    @Transactional
    public void createOpeningHour() throws Exception {
        int databaseSizeBeforeCreate = openingHourRepository.findAll().size();

        // Create the OpeningHour
        OpeningHourDTO openingHourDTO = openingHourMapper.toDto(openingHour);
        restOpeningHourMockMvc.perform(post("/api/opening-hours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(openingHourDTO)))
            .andExpect(status().isCreated());

        // Validate the OpeningHour in the database
        List<OpeningHour> openingHourList = openingHourRepository.findAll();
        assertThat(openingHourList).hasSize(databaseSizeBeforeCreate + 1);
        OpeningHour testOpeningHour = openingHourList.get(openingHourList.size() - 1);
        assertThat(testOpeningHour.getDay()).isEqualTo(DEFAULT_DAY);
        assertThat(testOpeningHour.getTime()).isEqualTo(DEFAULT_TIME);
    }

    @Test
    @Transactional
    public void createOpeningHourWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = openingHourRepository.findAll().size();

        // Create the OpeningHour with an existing ID
        openingHour.setId(1L);
        OpeningHourDTO openingHourDTO = openingHourMapper.toDto(openingHour);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpeningHourMockMvc.perform(post("/api/opening-hours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(openingHourDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpeningHour in the database
        List<OpeningHour> openingHourList = openingHourRepository.findAll();
        assertThat(openingHourList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOpeningHours() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

        // Get all the openingHourList
        restOpeningHourMockMvc.perform(get("/api/opening-hours?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(openingHour.getId().intValue())))
            .andExpect(jsonPath("$.[*].day").value(hasItem(DEFAULT_DAY.toString())))
            .andExpect(jsonPath("$.[*].time").value(hasItem(DEFAULT_TIME.toString())));
    }
    

    @Test
    @Transactional
    public void getOpeningHour() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

        // Get the openingHour
        restOpeningHourMockMvc.perform(get("/api/opening-hours/{id}", openingHour.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(openingHour.getId().intValue()))
            .andExpect(jsonPath("$.day").value(DEFAULT_DAY.toString()))
            .andExpect(jsonPath("$.time").value(DEFAULT_TIME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingOpeningHour() throws Exception {
        // Get the openingHour
        restOpeningHourMockMvc.perform(get("/api/opening-hours/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOpeningHour() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

        int databaseSizeBeforeUpdate = openingHourRepository.findAll().size();

        // Update the openingHour
        OpeningHour updatedOpeningHour = openingHourRepository.findById(openingHour.getId()).get();
        // Disconnect from session so that the updates on updatedOpeningHour are not directly saved in db
        em.detach(updatedOpeningHour);
        updatedOpeningHour
            .day(UPDATED_DAY)
            .time(UPDATED_TIME);
        OpeningHourDTO openingHourDTO = openingHourMapper.toDto(updatedOpeningHour);

        restOpeningHourMockMvc.perform(put("/api/opening-hours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(openingHourDTO)))
            .andExpect(status().isOk());

        // Validate the OpeningHour in the database
        List<OpeningHour> openingHourList = openingHourRepository.findAll();
        assertThat(openingHourList).hasSize(databaseSizeBeforeUpdate);
        OpeningHour testOpeningHour = openingHourList.get(openingHourList.size() - 1);
        assertThat(testOpeningHour.getDay()).isEqualTo(UPDATED_DAY);
        assertThat(testOpeningHour.getTime()).isEqualTo(UPDATED_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingOpeningHour() throws Exception {
        int databaseSizeBeforeUpdate = openingHourRepository.findAll().size();

        // Create the OpeningHour
        OpeningHourDTO openingHourDTO = openingHourMapper.toDto(openingHour);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOpeningHourMockMvc.perform(put("/api/opening-hours")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(openingHourDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OpeningHour in the database
        List<OpeningHour> openingHourList = openingHourRepository.findAll();
        assertThat(openingHourList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOpeningHour() throws Exception {
        // Initialize the database
        openingHourRepository.saveAndFlush(openingHour);

        int databaseSizeBeforeDelete = openingHourRepository.findAll().size();

        // Get the openingHour
        restOpeningHourMockMvc.perform(delete("/api/opening-hours/{id}", openingHour.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<OpeningHour> openingHourList = openingHourRepository.findAll();
        assertThat(openingHourList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpeningHour.class);
        OpeningHour openingHour1 = new OpeningHour();
        openingHour1.setId(1L);
        OpeningHour openingHour2 = new OpeningHour();
        openingHour2.setId(openingHour1.getId());
        assertThat(openingHour1).isEqualTo(openingHour2);
        openingHour2.setId(2L);
        assertThat(openingHour1).isNotEqualTo(openingHour2);
        openingHour1.setId(null);
        assertThat(openingHour1).isNotEqualTo(openingHour2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpeningHourDTO.class);
        OpeningHourDTO openingHourDTO1 = new OpeningHourDTO();
        openingHourDTO1.setId(1L);
        OpeningHourDTO openingHourDTO2 = new OpeningHourDTO();
        assertThat(openingHourDTO1).isNotEqualTo(openingHourDTO2);
        openingHourDTO2.setId(openingHourDTO1.getId());
        assertThat(openingHourDTO1).isEqualTo(openingHourDTO2);
        openingHourDTO2.setId(2L);
        assertThat(openingHourDTO1).isNotEqualTo(openingHourDTO2);
        openingHourDTO1.setId(null);
        assertThat(openingHourDTO1).isNotEqualTo(openingHourDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(openingHourMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(openingHourMapper.fromId(null)).isNull();
    }
}
