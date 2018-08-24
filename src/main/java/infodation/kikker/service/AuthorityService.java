package infodation.kikker.service;

import infodation.kikker.domain.Authority;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Authority.
 */
public interface AuthorityService {

    /**
     * Save a authority.
     *
     * @param authority the entity to save
     * @return the persisted entity
     */
    Authority save(Authority authority);

    /**
     * Get all the authorities.
     *
     * @return the list of entities
     */
    List<Authority> findAll();


    /**
     * Get the "id" authority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Authority> findOne(String id);

    /**
     * Delete the "id" authority.
     *
     * @param id the id of the entity
     */
    void delete(String id);
}
