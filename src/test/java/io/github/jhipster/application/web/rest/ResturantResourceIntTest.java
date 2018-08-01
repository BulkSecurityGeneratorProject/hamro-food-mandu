package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.HamrofoodmanduApp;

import io.github.jhipster.application.domain.Resturant;
import io.github.jhipster.application.repository.ResturantRepository;
import io.github.jhipster.application.service.ResturantService;
import io.github.jhipster.application.service.dto.ResturantDTO;
import io.github.jhipster.application.service.mapper.ResturantMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ResturantResource REST controller.
 *
 * @see ResturantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HamrofoodmanduApp.class)
public class ResturantResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Float DEFAULT_SERVICE_CHARGE = 1F;
    private static final Float UPDATED_SERVICE_CHARGE = 2F;

    private static final Float DEFAULT_VAT = 1F;
    private static final Float UPDATED_VAT = 2F;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ResturantRepository resturantRepository;
    @Mock
    private ResturantRepository resturantRepositoryMock;

    @Autowired
    private ResturantMapper resturantMapper;
    
    @Mock
    private ResturantService resturantServiceMock;

    @Autowired
    private ResturantService resturantService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restResturantMockMvc;

    private Resturant resturant;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ResturantResource resturantResource = new ResturantResource(resturantService);
        this.restResturantMockMvc = MockMvcBuilders.standaloneSetup(resturantResource)
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
    public static Resturant createEntity(EntityManager em) {
        Resturant resturant = new Resturant()
            .name(DEFAULT_NAME)
            .serviceCharge(DEFAULT_SERVICE_CHARGE)
            .vat(DEFAULT_VAT)
            .description(DEFAULT_DESCRIPTION);
        return resturant;
    }

    @Before
    public void initTest() {
        resturant = createEntity(em);
    }

    @Test
    @Transactional
    public void createResturant() throws Exception {
        int databaseSizeBeforeCreate = resturantRepository.findAll().size();

        // Create the Resturant
        ResturantDTO resturantDTO = resturantMapper.toDto(resturant);
        restResturantMockMvc.perform(post("/api/resturants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resturantDTO)))
            .andExpect(status().isCreated());

        // Validate the Resturant in the database
        List<Resturant> resturantList = resturantRepository.findAll();
        assertThat(resturantList).hasSize(databaseSizeBeforeCreate + 1);
        Resturant testResturant = resturantList.get(resturantList.size() - 1);
        assertThat(testResturant.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testResturant.getServiceCharge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testResturant.getVat()).isEqualTo(DEFAULT_VAT);
        assertThat(testResturant.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createResturantWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = resturantRepository.findAll().size();

        // Create the Resturant with an existing ID
        resturant.setId(1L);
        ResturantDTO resturantDTO = resturantMapper.toDto(resturant);

        // An entity with an existing ID cannot be created, so this API call must fail
        restResturantMockMvc.perform(post("/api/resturants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resturantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Resturant in the database
        List<Resturant> resturantList = resturantRepository.findAll();
        assertThat(resturantList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllResturants() throws Exception {
        // Initialize the database
        resturantRepository.saveAndFlush(resturant);

        // Get all the resturantList
        restResturantMockMvc.perform(get("/api/resturants?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resturant.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE.doubleValue())))
            .andExpect(jsonPath("$.[*].vat").value(hasItem(DEFAULT_VAT.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }
    
    public void getAllResturantsWithEagerRelationshipsIsEnabled() throws Exception {
        ResturantResource resturantResource = new ResturantResource(resturantServiceMock);
        when(resturantServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restResturantMockMvc = MockMvcBuilders.standaloneSetup(resturantResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restResturantMockMvc.perform(get("/api/resturants?eagerload=true"))
        .andExpect(status().isOk());

        verify(resturantServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllResturantsWithEagerRelationshipsIsNotEnabled() throws Exception {
        ResturantResource resturantResource = new ResturantResource(resturantServiceMock);
            when(resturantServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restResturantMockMvc = MockMvcBuilders.standaloneSetup(resturantResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restResturantMockMvc.perform(get("/api/resturants?eagerload=true"))
        .andExpect(status().isOk());

            verify(resturantServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getResturant() throws Exception {
        // Initialize the database
        resturantRepository.saveAndFlush(resturant);

        // Get the resturant
        restResturantMockMvc.perform(get("/api/resturants/{id}", resturant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(resturant.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.serviceCharge").value(DEFAULT_SERVICE_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.vat").value(DEFAULT_VAT.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingResturant() throws Exception {
        // Get the resturant
        restResturantMockMvc.perform(get("/api/resturants/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateResturant() throws Exception {
        // Initialize the database
        resturantRepository.saveAndFlush(resturant);

        int databaseSizeBeforeUpdate = resturantRepository.findAll().size();

        // Update the resturant
        Resturant updatedResturant = resturantRepository.findById(resturant.getId()).get();
        // Disconnect from session so that the updates on updatedResturant are not directly saved in db
        em.detach(updatedResturant);
        updatedResturant
            .name(UPDATED_NAME)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .vat(UPDATED_VAT)
            .description(UPDATED_DESCRIPTION);
        ResturantDTO resturantDTO = resturantMapper.toDto(updatedResturant);

        restResturantMockMvc.perform(put("/api/resturants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resturantDTO)))
            .andExpect(status().isOk());

        // Validate the Resturant in the database
        List<Resturant> resturantList = resturantRepository.findAll();
        assertThat(resturantList).hasSize(databaseSizeBeforeUpdate);
        Resturant testResturant = resturantList.get(resturantList.size() - 1);
        assertThat(testResturant.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testResturant.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testResturant.getVat()).isEqualTo(UPDATED_VAT);
        assertThat(testResturant.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingResturant() throws Exception {
        int databaseSizeBeforeUpdate = resturantRepository.findAll().size();

        // Create the Resturant
        ResturantDTO resturantDTO = resturantMapper.toDto(resturant);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restResturantMockMvc.perform(put("/api/resturants")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(resturantDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Resturant in the database
        List<Resturant> resturantList = resturantRepository.findAll();
        assertThat(resturantList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteResturant() throws Exception {
        // Initialize the database
        resturantRepository.saveAndFlush(resturant);

        int databaseSizeBeforeDelete = resturantRepository.findAll().size();

        // Get the resturant
        restResturantMockMvc.perform(delete("/api/resturants/{id}", resturant.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Resturant> resturantList = resturantRepository.findAll();
        assertThat(resturantList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resturant.class);
        Resturant resturant1 = new Resturant();
        resturant1.setId(1L);
        Resturant resturant2 = new Resturant();
        resturant2.setId(resturant1.getId());
        assertThat(resturant1).isEqualTo(resturant2);
        resturant2.setId(2L);
        assertThat(resturant1).isNotEqualTo(resturant2);
        resturant1.setId(null);
        assertThat(resturant1).isNotEqualTo(resturant2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ResturantDTO.class);
        ResturantDTO resturantDTO1 = new ResturantDTO();
        resturantDTO1.setId(1L);
        ResturantDTO resturantDTO2 = new ResturantDTO();
        assertThat(resturantDTO1).isNotEqualTo(resturantDTO2);
        resturantDTO2.setId(resturantDTO1.getId());
        assertThat(resturantDTO1).isEqualTo(resturantDTO2);
        resturantDTO2.setId(2L);
        assertThat(resturantDTO1).isNotEqualTo(resturantDTO2);
        resturantDTO1.setId(null);
        assertThat(resturantDTO1).isNotEqualTo(resturantDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(resturantMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(resturantMapper.fromId(null)).isNull();
    }
}
