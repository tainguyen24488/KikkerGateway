package infodation.kikker.web.rest;

import com.codahale.metrics.annotation.Timed;
import infodation.kikker.domain.Authority;
import infodation.kikker.domain.Function;
import infodation.kikker.domain.User;
import infodation.kikker.repository.UserRepository;
import infodation.kikker.security.AuthoritiesConstants;
import infodation.kikker.security.SecurityUtils;
import infodation.kikker.service.AuthorityService;
import infodation.kikker.service.UserService;
import infodation.kikker.web.rest.errors.BadRequestAlertException;
import infodation.kikker.web.rest.errors.InternalServerErrorException;
import infodation.kikker.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Authority.
 */
@RestController
@RequestMapping("/api")
public class AuthorityResource {

    private final Logger log = LoggerFactory.getLogger(AuthorityResource.class);

    private static final String ENTITY_NAME = "authority";

    private final AuthorityService authorityService;
    
    private final UserService userService;

    public AuthorityResource(UserService userService, AuthorityService authorityService) {
    	this.userService = userService;
        this.authorityService = authorityService;
    }

    /**
     * POST  /authorities : Create a new authority.
     *
     * @param authority the authority to create
     * @return the ResponseEntity with status 201 (Created) and with body the new authority, or with status 400 (Bad Request) if the authority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/authorities")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.BO})
    public ResponseEntity<Authority> createAuthority(@Valid @RequestBody Authority authority) throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authority);
        if (authority.getName() == null) {
            throw new BadRequestAlertException("A new authority cannot empty Name", ENTITY_NAME, "idnull");
        }
        Authority result = authorityService.save(authority);
        return ResponseEntity.created(new URI("/api/authorities/" + result.getName()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getName().toString()))
            .body(result);
    }

    /**
     * PUT  /authorities : Updates an existing authority.
     *
     * @param authority the authority to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated authority,
     * or with status 400 (Bad Request) if the authority is not valid,
     * or with status 500 (Internal Server Error) if the authority couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/authorities")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.BO})
    public ResponseEntity<Authority> updateAuthority(@Valid @RequestBody Authority authority) throws URISyntaxException {
        log.debug("REST request to update Authority : {}", authority);
        if (authority.getName() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Authority result = authorityService.save(authority);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, authority.getName().toString()))
            .body(result);
    }

    /**
     * GET  /authorities : get all the authorities.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of authorities in body
     */
    @GetMapping("/authorities")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.BO})
    public List<Authority> getAllAuthorities() {
        log.debug("REST request to get all Authorities");
        final Optional<String> userLogin = SecurityUtils.getCurrentUserLogin();
        if (!userLogin.isPresent()) {
            throw new InternalServerErrorException("User could not be found");
        }
        Optional<User> user = userService.getUserWithAuthorities();
        if (!user.isPresent()) {
            throw new InternalServerErrorException("User could not be found");
        }
        if(user.get().getAuthorities().stream().filter(au -> au.getName().equals(AuthoritiesConstants.ADMIN)).findFirst().isPresent())
        {
        	log.debug("REST request to get all Authorities with ROLE_ADMIN");
        	return authorityService.findAll();
        }
        	
        else{
        	return authorityService.findAllByBo();
        }
    }

    /**
     * GET  /authorities/:id : get the "id" authority.
     *
     * @param id the id of the authority to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the authority, or with status 404 (Not Found)
     */
    @GetMapping("/authorities/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.BO})
    public ResponseEntity<Authority> getAuthority(@PathVariable String id) {
        log.debug("REST request to get Authority : {}", id);
        Optional<Authority> authority = authorityService.findOne(id);
        authority.get().setFunctionIds(authority.get().getFunctions().stream().map(Function::getId)
	            .collect(Collectors.toList()));
        return ResponseUtil.wrapOrNotFound(authority);
    }

    /**
     * DELETE  /authorities/:id : delete the "id" authority.
     *
     * @param id the id of the authority to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/authorities/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.BO})
    public ResponseEntity<Void> deleteAuthority(@PathVariable String id) {
        log.debug("REST request to delete Authority : {}", id);
        authorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
