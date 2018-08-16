package infodation.kikker.repository;

import infodation.kikker.domain.Kikker_authority_function;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Kikker_authority_function entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Kikker_authority_functionRepository extends JpaRepository<Kikker_authority_function, Long> {

}
