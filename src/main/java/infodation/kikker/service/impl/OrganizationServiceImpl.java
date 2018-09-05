package infodation.kikker.service.impl;

import infodation.kikker.service.OrganizationService;
import infodation.kikker.domain.Organization;
import infodation.kikker.domain.User;
import infodation.kikker.domain.Organization;
import infodation.kikker.repository.OrganizationRepository;
import infodation.kikker.repository.UserRepository;
import infodation.kikker.security.AuthoritiesConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Organization.
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationRepository OrganizationRepository;
    
    private final UserRepository userRepository;

    public OrganizationServiceImpl(OrganizationRepository OrganizationRepository, UserRepository userRepository) {
        this.OrganizationRepository = OrganizationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Save a Organization.
     *
     * @param Organization the entity to save
     * @return the persisted entity
     */
    @Override
    public Organization save(Organization Organization) {
        log.debug("Request to save Organization : {}", Organization);        return OrganizationRepository.save(Organization);
    }

    /**
     * Get all the Organizations.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Organization> findAll(Long userId) {
        log.debug("Request to get all Organizations");
        //get userRole by Id
        User u = userRepository.findById(userId).get();
        if(u != null && u.getAuthorities().stream().filter(o -> o.getName().equals(AuthoritiesConstants.ADMIN)).findFirst().isPresent()) {
        	return OrganizationRepository.findAll();
        }
        return OrganizationRepository.findAllByUserId(userId);
    }
    
    /**
     * Get one Organization by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Organization> findOne(Long id) {
        log.debug("Request to get Organization : {}", id);
        return OrganizationRepository.findById(id);
    }

    /**
     * Delete the Organization by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        OrganizationRepository.deleteById(id);
    }
}
