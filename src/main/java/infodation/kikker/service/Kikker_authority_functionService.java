package infodation.kikker.service;

import infodation.kikker.domain.Kikker_authority_function;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Kikker_authority_function.
 */
public interface Kikker_authority_functionService {

    /**
     * Save a kikker_authority_function.
     *
     * @param kikker_authority_function the entity to save
     * @return the persisted entity
     */
    Kikker_authority_function save(Kikker_authority_function kikker_authority_function);

    /**
     * Get all the kikker_authority_functions.
     *
     * @return the list of entities
     */
    List<Kikker_authority_function> findAll();


    /**
     * Get the "id" kikker_authority_function.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Kikker_authority_function> findOne(Long id);

    /**
     * Delete the "id" kikker_authority_function.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
