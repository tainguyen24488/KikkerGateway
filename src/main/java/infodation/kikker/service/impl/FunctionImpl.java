package infodation.kikker.service.impl;

import infodation.kikker.service.FunctionService;
import infodation.kikker.domain.Function;
import infodation.kikker.repository.FunctionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Function.
 */
@Service
@Transactional
public class FunctionImpl implements FunctionService {

    private final Logger log = LoggerFactory.getLogger(FunctionImpl.class);

    private final FunctionRepository FunctionRepository;

    public FunctionImpl(FunctionRepository FunctionRepository) {
        this.FunctionRepository = FunctionRepository;
    }

    /**
     * Save a Function.
     *
     * @param Function the entity to save
     * @return the persisted entity
     */
    @Override
    public Function save(Function Function) {
        log.debug("Request to save Function : {}", Function);        return FunctionRepository.save(Function);
    }

    /**
     * Get all the Functions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Function> findAll() {
        log.debug("Request to get all Functions");
        return FunctionRepository.findAll();
    }


    /**
     * Get one Function by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Function> findOne(Long id) {
        log.debug("Request to get Function : {}", id);
        return FunctionRepository.findById(id);
    }

    /**
     * Delete the Function by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Function : {}", id);
        FunctionRepository.deleteById(id);
    }
}
