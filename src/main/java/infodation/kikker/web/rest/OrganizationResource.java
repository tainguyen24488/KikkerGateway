package infodation.kikker.web.rest;

import com.codahale.metrics.annotation.Timed;
import infodation.kikker.domain.Organization;
import infodation.kikker.service.OrganizationService;
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
 * REST controller for managing Organization.
 */
@RestController
@RequestMapping("/api")
public class OrganizationResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationResource.class);

    private static final String ENTITY_NAME = "Organization";

    private final OrganizationService OrganizationService;

    public OrganizationResource(OrganizationService OrganizationService) {
        this.OrganizationService = OrganizationService;
    }

    /**
     * POST  /kikker-organizations : Create a new Organization.
     *
     * @param Organization the Organization to create
     * @return the ResponseEntity with status 201 (Created) and with body the new Organization, or with status 400 (Bad Request) if the Organization has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/kikker-organizations")
    @Timed
    public ResponseEntity<Organization> createOrganization(@Valid @RequestBody Organization Organization) throws URISyntaxException {
        log.debug("REST request to save Organization : {}", Organization);
        if (Organization.getId() != null) {
            throw new BadRequestAlertException("A new Organization cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Organization result = OrganizationService.save(Organization);
        return ResponseEntity.created(new URI("/api/kikker-organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /kikker-organizations : Updates an existing Organization.
     *
     * @param Organization the Organization to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated Organization,
     * or with status 400 (Bad Request) if the Organization is not valid,
     * or with status 500 (Internal Server Error) if the Organization couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/kikker-organizations")
    @Timed
    public ResponseEntity<Organization> updateOrganization(@Valid @RequestBody Organization Organization) throws URISyntaxException {
        log.debug("REST request to update Organization : {}", Organization);
        if (Organization.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Organization result = OrganizationService.save(Organization);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, Organization.getId().toString()))
            .body(result);
    }

    /**
     * GET  /kikker-organizations : get all the Organizations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of Organizations in body
     */
    @GetMapping("/kikker-organizations")
    @Timed
    public List<Organization> getAllOrganizations() {
        log.debug("REST request to get all Organizations");
        return OrganizationService.findAll();
    }

    /**
     * GET  /kikker-organizations/:id : get the "id" Organization.
     *
     * @param id the id of the Organization to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the Organization, or with status 404 (Not Found)
     */
    @GetMapping("/kikker-organizations/{id}")
    @Timed
    public ResponseEntity<Organization> getOrganization(@PathVariable Long id) {
        log.debug("REST request to get Organization : {}", id);
        Optional<Organization> Organization = OrganizationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Organization);
    }

    /**
     * DELETE  /kikker-organizations/:id : delete the "id" Organization.
     *
     * @param id the id of the Organization to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/kikker-organizations/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        log.debug("REST request to delete Organization : {}", id);
        OrganizationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
