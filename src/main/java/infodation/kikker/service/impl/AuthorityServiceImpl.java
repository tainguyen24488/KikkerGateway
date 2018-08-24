package infodation.kikker.service.impl;

import infodation.kikker.service.AuthorityService;
import infodation.kikker.domain.Authority;
import infodation.kikker.domain.Function;
import infodation.kikker.domain.Organization;
import infodation.kikker.repository.AuthorityRepository;
import infodation.kikker.repository.FunctionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Authority.
 */
@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    private final Logger log = LoggerFactory.getLogger(AuthorityServiceImpl.class);

    private final AuthorityRepository authorityRepository;
    
    private final FunctionRepository functionRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository, FunctionRepository functionRepository) {
        this.authorityRepository = authorityRepository;
        this.functionRepository = functionRepository;
    }

    /**
     * Save a authority.
     *
     * @param authority the entity to save
     * @return the persisted entity
     */
    @Override
    public Authority save(Authority authority) {
        log.debug("Request to save Authority : {}", authority);
        if (authority.getFunctionIds().size() > 0) {
            Set<Function> functions = authority.getFunctionIds().stream()
                .map(functionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
            authority.setFunctions(functions);
        }
        log.debug("Request to save Authority : {}", authority);
        return authorityRepository.save(authority);
    }

    /**
     * Get all the authorities.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Authority> findAll() {
        log.debug("Request to get all Authorities");
        return authorityRepository.findAll();
    }


    /**
     * Get one authority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Authority> findOne(String id) {
        log.debug("Request to get Authority : {}", id);
        return authorityRepository.findById(id);
    }

    /**
     * Delete the authority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Authority : {}", id);
        authorityRepository.deleteById(id);
    }
}
