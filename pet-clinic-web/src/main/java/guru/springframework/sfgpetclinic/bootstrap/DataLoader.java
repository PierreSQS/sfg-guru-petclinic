package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        dog.setName("Fluppy");
        PetType savedDogPet = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Tessy");
        PetType savedCatPet = petTypeService.save(cat);
        log.info("####### Saved PetTypes {} {} #########", savedCatPet, savedDogPet);

        Owner owner1 = new Owner();
        owner1.setFirstName("Pierrot");
        owner1.setLastName("Mongonnam");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Craig");
        owner2.setLastName("Walls");

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Ken");
        owner3.setLastName("Kousen");

        ownerService.save(owner3);
        log.info("####### Saved Owners #########");

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
        log.info("####### Saved Vets #########");

    }
}
