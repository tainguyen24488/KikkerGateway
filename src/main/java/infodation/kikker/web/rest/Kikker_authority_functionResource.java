package infodation.kikker.web.rest;

import com.codahale.metrics.annotation.Timed;
import infodation.kikker.domain.Kikker_authority_function;
import infodation.kikker.service.Kikker_authority_functionService;
import infodation.kikker.web.rest.errors.BadRequestAlertException;
import infodation.kikker.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Kikker_authority_function.
 */
@RestController
@RequestMapping("/api")
public class Kikker_authority_functionResource {

    private final Logger log = LoggerFactory.getLogger(Kikker_authority_functionResource.class);

    private static final String ENTITY_NAME = "kikker_authority_function";

    private final Kikker_authority_functionService kikker_authority_functionService;

    public Kikker_authority_functionResource(Kikker_authority_functionService kikker_authority_functionService) {
        this.kikker_authority_functionService = kikker_authority_functionService;
    }

    /**
     * POST  /kikker-authority-functions : Create a new kikker_authority_function.
     *
     * @param kikker_authority_function the kikker_authority_function to create
     * @return the ResponseEntity with status 201 (Created) and with body the new kikker_authority_function, or with status 400 (Bad Request) if the kikker_authority_function has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/kikker-authority-functions")
    @Timed
    public ResponseEntity<Kikker_authority_function> createKikker_authority_function(@Valid @RequestBody Kikker_authority_function kikker_authority_function) throws URISyntaxException {
        log.debug("REST request to save Kikker_authority_function : {}", kikker_authority_function);
        if (kikker_authority_function.getId() != null) {
            throw new BadRequestAlertException("A new kikker_authority_function cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Kikker_authority_function result = kikker_authority_functionService.save(kikker_authority_function);
        return ResponseEntity.created(new URI("/api/kikker-authority-functions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kikker-authority-functions : Updates an existing kikker_authority_function.
     *
     * @param kikker_authority_function the kikker_authority_function to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated kikker_authority_function,
     * or with status 400 (Bad Request) if the kikker_authority_function is not valid,
     * or with status 500 (Internal Server Error) if the kikker_authority_function couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/kikker-authority-functions")
    @Timed
    public ResponseEntity<Kikker_authority_function> updateKikker_authority_function(@Valid @RequestBody Kikker_authority_function kikker_authority_function) throws URISyntaxException {
        log.debug("REST request to update Kikker_authority_function : {}", kikker_authority_function);
        if (kikker_authority_function.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Kikker_authority_function result = kikker_authority_functionService.save(kikker_authority_function);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, kikker_authority_function.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kikker-authority-functions : get all the kikker_authority_functions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of kikker_authority_functions in body
     */
    @GetMapping("/kikker-authority-functions")
    @Timed
    public List<Kikker_authority_function> getAllKikker_authority_functions() {
        log.debug("REST request to get all Kikker_authority_functions");
        return kikker_authority_functionService.findAll();
    }

    /**
     * GET  /kikker-authority-functions/:id : get the "id" kikker_authority_function.
     *
     * @param id the id of the kikker_authority_function to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the kikker_authority_function, or with status 404 (Not Found)
     */
    @GetMapping("/kikker-authority-functions/{id}")
    @Timed
    public ResponseEntity<Kikker_authority_function> getKikker_authority_function(@PathVariable Long id) {
        log.debug("REST request to get Kikker_authority_function : {}", id);
        Optional<Kikker_authority_function> kikker_authority_function = kikker_authority_functionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kikker_authority_function);
    }

    /**
     * DELETE  /kikker-authority-functions/:id : delete the "id" kikker_authority_function.
     *
     * @param id the id of the kikker_authority_function to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/kikker-authority-functions/{id}")
    @Timed
    public ResponseEntity<Void> deleteKikker_authority_function(@PathVariable Long id) {
        log.debug("REST request to delete Kikker_authority_function : {}", id);
        kikker_authority_functionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
