package infodation.kikker.web.rest;

import infodation.kikker.KikkerGatewayApp;

import infodation.kikker.domain.Kikker_authority_function;
import infodation.kikker.repository.Kikker_authority_functionRepository;
import infodation.kikker.service.Kikker_authority_functionService;
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
 * Test class for the Kikker_authority_functionResource REST controller.
 *
 * @see Kikker_authority_functionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KikkerGatewayApp.class)
public class Kikker_authority_functionResourceIntTest {

    private static final Long DEFAULT_AUTHORITY_ID = 1L;
    private static final Long UPDATED_AUTHORITY_ID = 2L;

    private static final Integer DEFAULT_FUNCTION_ID = 1;
    private static final Integer UPDATED_FUNCTION_ID = 2;

    @Autowired
    private Kikker_authority_functionRepository kikker_authority_functionRepository;

    

    @Autowired
    private Kikker_authority_functionService kikker_authority_functionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restKikker_authority_functionMockMvc;

    private Kikker_authority_function kikker_authority_function;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Kikker_authority_functionResource kikker_authority_functionResource = new Kikker_authority_functionResource(kikker_authority_functionService);
        this.restKikker_authority_functionMockMvc = MockMvcBuilders.standaloneSetup(kikker_authority_functionResource)
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
    public static Kikker_authority_function createEntity(EntityManager em) {
        Kikker_authority_function kikker_authority_function = new Kikker_authority_function()
            .authority_id(DEFAULT_AUTHORITY_ID)
            .function_id(DEFAULT_FUNCTION_ID);
        return kikker_authority_function;
    }

    @Before
    public void initTest() {
        kikker_authority_function = createEntity(em);
    }

    @Test
    @Transactional
    public void createKikker_authority_function() throws Exception {
        int databaseSizeBeforeCreate = kikker_authority_functionRepository.findAll().size();

        // Create the Kikker_authority_function
        restKikker_authority_functionMockMvc.perform(post("/api/kikker-authority-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_authority_function)))
            .andExpect(status().isCreated());

        // Validate the Kikker_authority_function in the database
        List<Kikker_authority_function> kikker_authority_functionList = kikker_authority_functionRepository.findAll();
        assertThat(kikker_authority_functionList).hasSize(databaseSizeBeforeCreate + 1);
        Kikker_authority_function testKikker_authority_function = kikker_authority_functionList.get(kikker_authority_functionList.size() - 1);
        assertThat(testKikker_authority_function.getAuthority_id()).isEqualTo(DEFAULT_AUTHORITY_ID);
        assertThat(testKikker_authority_function.getFunction_id()).isEqualTo(DEFAULT_FUNCTION_ID);
    }

    @Test
    @Transactional
    public void createKikker_authority_functionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kikker_authority_functionRepository.findAll().size();

        // Create the Kikker_authority_function with an existing ID
        kikker_authority_function.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKikker_authority_functionMockMvc.perform(post("/api/kikker-authority-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_authority_function)))
            .andExpect(status().isBadRequest());

        // Validate the Kikker_authority_function in the database
        List<Kikker_authority_function> kikker_authority_functionList = kikker_authority_functionRepository.findAll();
        assertThat(kikker_authority_functionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAuthority_idIsRequired() throws Exception {
        int databaseSizeBeforeTest = kikker_authority_functionRepository.findAll().size();
        // set the field null
        kikker_authority_function.setAuthority_id(null);

        // Create the Kikker_authority_function, which fails.

        restKikker_authority_functionMockMvc.perform(post("/api/kikker-authority-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_authority_function)))
            .andExpect(status().isBadRequest());

        List<Kikker_authority_function> kikker_authority_functionList = kikker_authority_functionRepository.findAll();
        assertThat(kikker_authority_functionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFunction_idIsRequired() throws Exception {
        int databaseSizeBeforeTest = kikker_authority_functionRepository.findAll().size();
        // set the field null
        kikker_authority_function.setFunction_id(null);

        // Create the Kikker_authority_function, which fails.

        restKikker_authority_functionMockMvc.perform(post("/api/kikker-authority-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_authority_function)))
            .andExpect(status().isBadRequest());

        List<Kikker_authority_function> kikker_authority_functionList = kikker_authority_functionRepository.findAll();
        assertThat(kikker_authority_functionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKikker_authority_functions() throws Exception {
        // Initialize the database
        kikker_authority_functionRepository.saveAndFlush(kikker_authority_function);

        // Get all the kikker_authority_functionList
        restKikker_authority_functionMockMvc.perform(get("/api/kikker-authority-functions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kikker_authority_function.getId().intValue())))
            .andExpect(jsonPath("$.[*].authority_id").value(hasItem(DEFAULT_AUTHORITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].function_id").value(hasItem(DEFAULT_FUNCTION_ID)));
    }
    

    @Test
    @Transactional
    public void getKikker_authority_function() throws Exception {
        // Initialize the database
        kikker_authority_functionRepository.saveAndFlush(kikker_authority_function);

        // Get the kikker_authority_function
        restKikker_authority_functionMockMvc.perform(get("/api/kikker-authority-functions/{id}", kikker_authority_function.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(kikker_authority_function.getId().intValue()))
            .andExpect(jsonPath("$.authority_id").value(DEFAULT_AUTHORITY_ID.intValue()))
            .andExpect(jsonPath("$.function_id").value(DEFAULT_FUNCTION_ID));
    }
    @Test
    @Transactional
    public void getNonExistingKikker_authority_function() throws Exception {
        // Get the kikker_authority_function
        restKikker_authority_functionMockMvc.perform(get("/api/kikker-authority-functions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKikker_authority_function() throws Exception {
        // Initialize the database
        kikker_authority_functionService.save(kikker_authority_function);

        int databaseSizeBeforeUpdate = kikker_authority_functionRepository.findAll().size();

        // Update the kikker_authority_function
        Kikker_authority_function updatedKikker_authority_function = kikker_authority_functionRepository.findById(kikker_authority_function.getId()).get();
        // Disconnect from session so that the updates on updatedKikker_authority_function are not directly saved in db
        em.detach(updatedKikker_authority_function);
        updatedKikker_authority_function
            .authority_id(UPDATED_AUTHORITY_ID)
            .function_id(UPDATED_FUNCTION_ID);

        restKikker_authority_functionMockMvc.perform(put("/api/kikker-authority-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedKikker_authority_function)))
            .andExpect(status().isOk());

        // Validate the Kikker_authority_function in the database
        List<Kikker_authority_function> kikker_authority_functionList = kikker_authority_functionRepository.findAll();
        assertThat(kikker_authority_functionList).hasSize(databaseSizeBeforeUpdate);
        Kikker_authority_function testKikker_authority_function = kikker_authority_functionList.get(kikker_authority_functionList.size() - 1);
        assertThat(testKikker_authority_function.getAuthority_id()).isEqualTo(UPDATED_AUTHORITY_ID);
        assertThat(testKikker_authority_function.getFunction_id()).isEqualTo(UPDATED_FUNCTION_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingKikker_authority_function() throws Exception {
        int databaseSizeBeforeUpdate = kikker_authority_functionRepository.findAll().size();

        // Create the Kikker_authority_function

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restKikker_authority_functionMockMvc.perform(put("/api/kikker-authority-functions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(kikker_authority_function)))
            .andExpect(status().isBadRequest());

        // Validate the Kikker_authority_function in the database
        List<Kikker_authority_function> kikker_authority_functionList = kikker_authority_functionRepository.findAll();
        assertThat(kikker_authority_functionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKikker_authority_function() throws Exception {
        // Initialize the database
        kikker_authority_functionService.save(kikker_authority_function);

        int databaseSizeBeforeDelete = kikker_authority_functionRepository.findAll().size();

        // Get the kikker_authority_function
        restKikker_authority_functionMockMvc.perform(delete("/api/kikker-authority-functions/{id}", kikker_authority_function.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Kikker_authority_function> kikker_authority_functionList = kikker_authority_functionRepository.findAll();
        assertThat(kikker_authority_functionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Kikker_authority_function.class);
        Kikker_authority_function kikker_authority_function1 = new Kikker_authority_function();
        kikker_authority_function1.setId(1L);
        Kikker_authority_function kikker_authority_function2 = new Kikker_authority_function();
        kikker_authority_function2.setId(kikker_authority_function1.getId());
        assertThat(kikker_authority_function1).isEqualTo(kikker_authority_function2);
        kikker_authority_function2.setId(2L);
        assertThat(kikker_authority_function1).isNotEqualTo(kikker_authority_function2);
        kikker_authority_function1.setId(null);
        assertThat(kikker_authority_function1).isNotEqualTo(kikker_authority_function2);
    }
}
