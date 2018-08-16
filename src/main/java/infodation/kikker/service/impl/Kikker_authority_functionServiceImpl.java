package infodation.kikker.service.impl;

import infodation.kikker.service.Kikker_authority_functionService;
import infodation.kikker.domain.Kikker_authority_function;
import infodation.kikker.repository.Kikker_authority_functionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Kikker_authority_function.
 */
@Service
@Transactional
public class Kikker_authority_functionServiceImpl implements Kikker_authority_functionService {

    private final Logger log = LoggerFactory.getLogger(Kikker_authority_functionServiceImpl.class);

    private final Kikker_authority_functionRepository kikker_authority_functionRepository;

    public Kikker_authority_functionServiceImpl(Kikker_authority_functionRepository kikker_authority_functionRepository) {
        this.kikker_authority_functionRepository = kikker_authority_functionRepository;
    }

    /**
     * Save a kikker_authority_function.
     *
     * @param kikker_authority_function the entity to save
     * @return the persisted entity
     */
    @Override
    public Kikker_authority_function save(Kikker_authority_function kikker_authority_function) {
        log.debug("Request to save Kikker_authority_function : {}", kikker_authority_function);        return kikker_authority_functionRepository.save(kikker_authority_function);
    }

    /**
     * Get all the kikker_authority_functions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Kikker_authority_function> findAll() {
        log.debug("Request to get all Kikker_authority_functions");
        return kikker_authority_functionRepository.findAll();
    }


    /**
     * Get one kikker_authority_function by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Kikker_authority_function> findOne(Long id) {
        log.debug("Request to get Kikker_authority_function : {}", id);
        return kikker_authority_functionRepository.findById(id);
    }

    /**
     * Delete the kikker_authority_function by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Kikker_authority_function : {}", id);
        kikker_authority_functionRepository.deleteById(id);
    }
}
