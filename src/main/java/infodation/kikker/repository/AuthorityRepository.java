package infodation.kikker.repository;

import infodation.kikker.domain.Authority;
import infodation.kikker.domain.Function;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
	
	public List<Authority> findAllByOrderByNameAsc();
	
	public List<Authority> findByNameNotLike(String name);
}
