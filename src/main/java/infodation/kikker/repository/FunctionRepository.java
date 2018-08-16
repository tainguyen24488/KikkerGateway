package infodation.kikker.repository;

import infodation.kikker.domain.Function;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Kikker_funtion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {

}
