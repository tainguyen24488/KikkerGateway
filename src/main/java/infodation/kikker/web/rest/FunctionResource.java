package infodation.kikker.web.rest;

import com.codahale.metrics.annotation.Timed;
import infodation.kikker.domain.Function;
import infodation.kikker.service.FunctionService;
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
 * REST controller for managing Function.
 */
@RestController
@RequestMapping("/api")
public class FunctionResource {

    private final Logger log = LoggerFactory.getLogger(FunctionResource.class);

    private static final String ENTITY_NAME = "Function";

    private final FunctionService functionService;

    public FunctionResource(FunctionService Function) {
        this.functionService = Function;
    }

    /**
     * POST  /kikker-funtions : Create a new Function.
     *
     * @param Function the Function to create
     * @return the ResponseEntity with status 201 (Created) and with body the new Function, or with status 400 (Bad Request) if the Function has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/kikker-funtions")
    @Timed
    public ResponseEntity<Function> createFunction(@Valid @RequestBody Function Function) throws URISyntaxException {
        log.debug("REST request to save Function : {}", Function);
        if (Function.getId() != null) {
            throw new BadRequestAlertException("A new Function cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Function result = functionService.save(Function);
        return ResponseEntity.created(new URI("/api/kikker-funtions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kikker-funtions : Updates an existing Function.
     *
     * @param Function the Function to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated Function,
     * or with status 400 (Bad Request) if the Function is not valid,
     * or with status 500 (Internal Server Error) if the Function couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/kikker-funtions")
    @Timed
    public ResponseEntity<Function> updateFunction(@Valid @RequestBody Function Function) throws URISyntaxException {
        log.debug("REST request to update Function : {}", Function);
        if (Function.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Function result = functionService.save(Function);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Function.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kikker-funtions : get all the Functions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of Functions in body
     */
    @GetMapping("/kikker-funtions")
    @Timed
    public List<Function> getAllFunctions() {
        log.debug("REST request to get all Functions");
        return functionService.findAll();
    }

    /**
     * GET  /kikker-funtions/:id : get the "id" Function.
     *
     * @param id the id of the Function to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the Function, or with status 404 (Not Found)
     */
    @GetMapping("/kikker-funtions/{id}")
    @Timed
    public ResponseEntity<Function> getFunction(@PathVariable Long id) {
        log.debug("REST request to get Function : {}", id);
        Optional<Function> Function = functionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Function);
    }

    /**
     * DELETE  /kikker-funtions/:id : delete the "id" Function.
     *
     * @param id the id of the Function to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/kikker-funtions/{id}")
    @Timed
    public ResponseEntity<Void> deleteFunction(@PathVariable Long id) {
        log.debug("REST request to delete Function : {}", id);
        functionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
