package pl.arek.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.arek.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
