package infodation.kikker.service;

import infodation.kikker.domain.Function;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Function.
 */
public interface FunctionService {

    /**
     * Save a Function.
     *
     * @param Function the entity to save
     * @return the persisted entity
     */
    Function save(Function Function);

    /**
     * Get all the Functions.
     *
     * @return the list of entities
     */
    List<Function> findAll();


    /**
     * Get the "id" Function.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Function> findOne(Long id);

    /**
     * Delete the "id" Function.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
