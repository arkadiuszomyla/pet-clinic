package pl.arek.petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.arek.petclinic.model.Owner;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Smith";


    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingID() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner ownerSaved = ownerMapService.save(owner2);
        assertEquals(id, ownerSaved.getId());
    }

    @Test
    void ownerNoId() {
        Owner ownerSaved = Owner.builder().build();
        ownerMapService.save(ownerSaved);
        assertNotNull(ownerSaved);
        assertNotNull(ownerSaved.getId());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);
        assertNotNull(smith);
        assertEquals(ownerId, smith.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerMapService.findByLastName("fooo");
        assertNull(smith);
    }

}