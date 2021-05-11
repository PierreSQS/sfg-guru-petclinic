package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
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
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {

        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedPetTypeDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedPetTypeCat = petTypeService.save(cat);
        log.info("####### Saved PetTypes {} {} #########", savedPetTypeCat, savedPetTypeDog);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedSpecialityRadio = specialityService.save(radiology);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedSpecialityDent = specialityService.save(dentistry);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSpecialitySurg = specialityService.save(surgery);

        Owner owner1 = new Owner();
        Pet pet1 = new Pet();
        pet1.setPetType(savedPetTypeDog);
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

        Visit firstVisitOfPet2 = new Visit();
        firstVisitOfPet2.setDate(LocalDate.of(2021,4,17));
        firstVisitOfPet2.setReason("Sneezy Kitty");

        Owner owner2 = new Owner();
        Pet pet2 = new Pet();
        pet2.setPetType(savedPetTypeCat);
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

        // Fixes the issue of invalid Visit
        // SAVE THE VISIT ONLY AFTER THE OWNER HAS BEEN SAVED!!!!
        firstVisitOfPet2.setPet(pet2);
        visitService.save(firstVisitOfPet2);

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
        vet1.getSpecialities().add(savedSpecialityRadio);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Adamou");
        vet2.setLastName("Ndam Njoya");
        vet2.getSpecialities().add(savedSpecialityDent);

        vetService.save(vet2);

        Vet vet3 = new Vet();
        vet3.setFirstName("Nathalie");
        vet3.setLastName("Nkoa");
        vet3.getSpecialities().add(savedSpecialitySurg);

        vetService.save(vet3);
        log.info("####### Vets saved #########");
    }
}
