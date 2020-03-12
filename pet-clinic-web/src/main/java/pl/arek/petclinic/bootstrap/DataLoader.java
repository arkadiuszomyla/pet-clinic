package pl.arek.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.arek.petclinic.model.Owner;
import pl.arek.petclinic.model.Pet;
import pl.arek.petclinic.model.PetType;
import pl.arek.petclinic.model.Vet;
import pl.arek.petclinic.services.OwnerService;
import pl.arek.petclinic.services.PetTypeService;
import pl.arek.petclinic.services.VetService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCatPetType = petTypeService.save(cat);

        System.out.println("Loaded PetType...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("666 666 666");
        Pet michaelsPet = new Pet();
        michaelsPet.setName("Rosco");
        michaelsPet.setOwner(owner1);
        michaelsPet.setPetType(savedDogPetType);
        michaelsPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(michaelsPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("666 666 666");
        Pet fionasPet = new Pet();
        fionasPet.setName("Puszek");
        fionasPet.setOwner(owner2);
        fionasPet.setPetType(saveCatPetType);
        fionasPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(fionasPet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
