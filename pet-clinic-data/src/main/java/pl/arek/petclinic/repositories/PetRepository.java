package pl.arek.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.arek.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
