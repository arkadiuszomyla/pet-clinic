package pl.arek.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.arek.petclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
