package pl.arek.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.arek.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
