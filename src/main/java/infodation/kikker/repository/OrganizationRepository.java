package infodation.kikker.repository;

import infodation.kikker.domain.Organization;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Kikker_organization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	@Query("select r from Organization r join r.users ur where ur.id = :userId")
	List<Organization> findAllByUserId(@Param("userId") Long userId);
	
}
