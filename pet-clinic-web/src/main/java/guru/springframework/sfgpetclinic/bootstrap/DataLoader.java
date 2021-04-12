package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader() {
        this.ownerService = new OwnerMapService();
        this.vetService = new VetMapService();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Pierrot");
        owner1.setLastName("Mongonnam");

        ownerService.save(owner1);
        Owner owner2 = new Owner();
        owner1.setFirstName("Craig");
        owner1.setLastName("Walls");

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner1.setFirstName("Ken");
        owner1.setLastName("Kousen");

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
