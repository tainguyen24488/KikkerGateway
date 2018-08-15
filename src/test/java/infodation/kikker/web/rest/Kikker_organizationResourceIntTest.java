package infodation.kikker.web.rest;

import infodation.kikker.KikkerGatewayApp;

import infodation.kikker.domain.Kikker_organization;
import infodation.kikker.repository.Kikker_organizationRepository;
import infodation.kikker.service.Kikker_organizationService;
import infodation.kikker.web.rest.errors.ExceptionTranslator;

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


import static infodation.kikker.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Kikker_organizationResource REST controller.
 *
 * @see Kikker_organizationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KikkerGatewayApp.class)
public class Kikker_organizationResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ZIPCODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIPCODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOUSE_NR = 1;
    private static final Integer UPDATED_HOUSE_NR = 2;

    private static final String DEFAULT_HOUSE_NR_EXT = "AAAAAAAAAA";
    private static final String UPDATED_HOUSE_NR_EXT = "BBBBBBBBBB";

    @Autowired
    private Kikker_organizationRepository kikker_organizationRepository;

    

    @Autowired
    private Kikker_organizationService kikker_organizationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKikker_organizationMockMvc;

    private Kikker_organization kikker_organization;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Kikker_organizationResource kikker_organizationResource = new Kikker_organizationResource(kikker_organizationService);
        this.restKikker_organizationMockMvc = MockMvcBuilders.standaloneSetup(kikker_organizationResource)
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
    public static Kikker_organization createEntity(EntityManager em) {
        Kikker_organization kikker_organization = new Kikker_organization()
            .name(DEFAULT_NAME)
            .zipcode(DEFAULT_ZIPCODE)
            .house_nr(DEFAULT_HOUSE_NR)
            .house_nr_ext(DEFAULT_HOUSE_NR_EXT);
        return kikker_organization;
    }

    @Before
    public void initTest() {
        kikker_organization = createEntity(em);
    }

    @Test
    @Transactional
    public void createKikker_organization() throws Exception {
        int databaseSizeBeforeCreate = kikker_organizationRepository.findAll().size();

        // Create the Kikker_organization
        restKikker_organizationMockMvc.perform(post("/api/kikker-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_organization)))
            .andExpect(status().isCreated());

        // Validate the Kikker_organization in the database
        List<Kikker_organization> kikker_organizationList = kikker_organizationRepository.findAll();
        assertThat(kikker_organizationList).hasSize(databaseSizeBeforeCreate + 1);
        Kikker_organization testKikker_organization = kikker_organizationList.get(kikker_organizationList.size() - 1);
        assertThat(testKikker_organization.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testKikker_organization.getZipcode()).isEqualTo(DEFAULT_ZIPCODE);
        assertThat(testKikker_organization.getHouse_nr()).isEqualTo(DEFAULT_HOUSE_NR);
        assertThat(testKikker_organization.getHouse_nr_ext()).isEqualTo(DEFAULT_HOUSE_NR_EXT);
    }

    @Test
    @Transactional
    public void createKikker_organizationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kikker_organizationRepository.findAll().size();

        // Create the Kikker_organization with an existing ID
        kikker_organization.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKikker_organizationMockMvc.perform(post("/api/kikker-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_organization)))
            .andExpect(status().isBadRequest());

        // Validate the Kikker_organization in the database
        List<Kikker_organization> kikker_organizationList = kikker_organizationRepository.findAll();
        assertThat(kikker_organizationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = kikker_organizationRepository.findAll().size();
        // set the field null
        kikker_organization.setName(null);

        // Create the Kikker_organization, which fails.

        restKikker_organizationMockMvc.perform(post("/api/kikker-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_organization)))
            .andExpect(status().isBadRequest());

        List<Kikker_organization> kikker_organizationList = kikker_organizationRepository.findAll();
        assertThat(kikker_organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZipcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = kikker_organizationRepository.findAll().size();
        // set the field null
        kikker_organization.setZipcode(null);

        // Create the Kikker_organization, which fails.

        restKikker_organizationMockMvc.perform(post("/api/kikker-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_organization)))
            .andExpect(status().isBadRequest());

        List<Kikker_organization> kikker_organizationList = kikker_organizationRepository.findAll();
        assertThat(kikker_organizationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKikker_organizations() throws Exception {
        // Initialize the database
        kikker_organizationRepository.saveAndFlush(kikker_organization);

        // Get all the kikker_organizationList
        restKikker_organizationMockMvc.perform(get("/api/kikker-organizations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kikker_organization.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].zipcode").value(hasItem(DEFAULT_ZIPCODE.toString())))
            .andExpect(jsonPath("$.[*].house_nr").value(hasItem(DEFAULT_HOUSE_NR)))
            .andExpect(jsonPath("$.[*].house_nr_ext").value(hasItem(DEFAULT_HOUSE_NR_EXT.toString())));
    }
    

    @Test
    @Transactional
    public void getKikker_organization() throws Exception {
        // Initialize the database
        kikker_organizationRepository.saveAndFlush(kikker_organization);

        // Get the kikker_organization
        restKikker_organizationMockMvc.perform(get("/api/kikker-organizations/{id}", kikker_organization.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kikker_organization.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.zipcode").value(DEFAULT_ZIPCODE.toString()))
            .andExpect(jsonPath("$.house_nr").value(DEFAULT_HOUSE_NR))
            .andExpect(jsonPath("$.house_nr_ext").value(DEFAULT_HOUSE_NR_EXT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingKikker_organization() throws Exception {
        // Get the kikker_organization
        restKikker_organizationMockMvc.perform(get("/api/kikker-organizations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKikker_organization() throws Exception {
        // Initialize the database
        kikker_organizationService.save(kikker_organization);

        int databaseSizeBeforeUpdate = kikker_organizationRepository.findAll().size();

        // Update the kikker_organization
        Kikker_organization updatedKikker_organization = kikker_organizationRepository.findById(kikker_organization.getId()).get();
        // Disconnect from session so that the updates on updatedKikker_organization are not directly saved in db
        em.detach(updatedKikker_organization);
        updatedKikker_organization
            .name(UPDATED_NAME)
            .zipcode(UPDATED_ZIPCODE)
            .house_nr(UPDATED_HOUSE_NR)
            .house_nr_ext(UPDATED_HOUSE_NR_EXT);

        restKikker_organizationMockMvc.perform(put("/api/kikker-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKikker_organization)))
            .andExpect(status().isOk());

        // Validate the Kikker_organization in the database
        List<Kikker_organization> kikker_organizationList = kikker_organizationRepository.findAll();
        assertThat(kikker_organizationList).hasSize(databaseSizeBeforeUpdate);
        Kikker_organization testKikker_organization = kikker_organizationList.get(kikker_organizationList.size() - 1);
        assertThat(testKikker_organization.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testKikker_organization.getZipcode()).isEqualTo(UPDATED_ZIPCODE);
        assertThat(testKikker_organization.getHouse_nr()).isEqualTo(UPDATED_HOUSE_NR);
        assertThat(testKikker_organization.getHouse_nr_ext()).isEqualTo(UPDATED_HOUSE_NR_EXT);
    }

    @Test
    @Transactional
    public void updateNonExistingKikker_organization() throws Exception {
        int databaseSizeBeforeUpdate = kikker_organizationRepository.findAll().size();

        // Create the Kikker_organization

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restKikker_organizationMockMvc.perform(put("/api/kikker-organizations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_organization)))
            .andExpect(status().isBadRequest());

        // Validate the Kikker_organization in the database
        List<Kikker_organization> kikker_organizationList = kikker_organizationRepository.findAll();
        assertThat(kikker_organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKikker_organization() throws Exception {
        // Initialize the database
        kikker_organizationService.save(kikker_organization);

        int databaseSizeBeforeDelete = kikker_organizationRepository.findAll().size();

        // Get the kikker_organization
        restKikker_organizationMockMvc.perform(delete("/api/kikker-organizations/{id}", kikker_organization.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Kikker_organization> kikker_organizationList = kikker_organizationRepository.findAll();
        assertThat(kikker_organizationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kikker_organization.class);
        Kikker_organization kikker_organization1 = new Kikker_organization();
        kikker_organization1.setId(1L);
        Kikker_organization kikker_organization2 = new Kikker_organization();
        kikker_organization2.setId(kikker_organization1.getId());
        assertThat(kikker_organization1).isEqualTo(kikker_organization2);
        kikker_organization2.setId(2L);
        assertThat(kikker_organization1).isNotEqualTo(kikker_organization2);
        kikker_organization1.setId(null);
        assertThat(kikker_organization1).isNotEqualTo(kikker_organization2);
    }
}
