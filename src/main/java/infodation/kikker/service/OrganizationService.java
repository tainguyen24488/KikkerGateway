package infodation.kikker.service;

import java.util.List;
import java.util.Optional;

import infodation.kikker.domain.Organization;

/**
 * Service Interface for managing Kikker_organization.
 */
public interface OrganizationService {

    /**
     * Save a kikker_organization.
     *
     * @param kikker_organization the entity to save
     * @return the persisted entity
     */
	Organization save(Organization kikker_organization);

    /**
     * Get all the kikker_organizations.
     *
     * @return the list of entities
     */
    List<Organization> findAll(Long userId);


    /**
     * Get the "id" kikker_organization.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Organization> findOne(Long id);

    /**
     * Delete the "id" kikker_organization.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
