package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
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
        PetType savedDogPet = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPet = petTypeService.save(cat);
        log.info("####### Saved PetTypes {} {} #########", savedCatPet, savedDogPet);

        Owner owner1 = new Owner();
        Pet pet1 = new Pet();
        pet1.setPetType(savedDogPet);
        pet1.setBirthDate(LocalDate.of(2011, 1, 1));
        pet1.setName("Fluppy");
        pet1.setOwner(owner1);

        owner1.setFirstName("Pierrot");
        owner1.setLastName("Mongonnam");
        owner1.setAddress("Rue Douala Manga");
        owner1.setCity("Douala");
        owner1.setPhone("123232323");
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        Pet pet2 = new Pet();
        pet2.setPetType(savedCatPet);
        pet2.setBirthDate(LocalDate.of(2012, 2, 2));
        pet2.setName("Tessy");
        pet2.setOwner(owner2);

        owner2.setFirstName("Craig");
        owner2.setLastName("Walls");
        owner2.setAddress("Wall Street");
        owner2.setCity("New York");
        owner2.setPhone("234334");
        owner2.getPets().add(pet2);

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Ken");
        owner3.setLastName("Kousen");
        owner3.setAddress("Roosevelt Street");
        owner3.setCity("Malborough");
        owner3.setPhone("3454545");

        ownerService.save(owner3);
        log.info("####### Owners saved #########");

        Vet vet1 = new Vet();
        vet1.setFirstName("Paul");
        vet1.setLastName("Owona");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Adamou");
        vet2.setLastName("Ndam Njoya");

        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Nathalie");
        vet3.setLastName("Nkoa");

        vetService.save(vet3);
        log.info("####### Vets saved #########");

    }
}
