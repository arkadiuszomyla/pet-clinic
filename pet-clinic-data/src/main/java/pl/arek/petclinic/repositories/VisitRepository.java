package pl.arek.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.arek.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
