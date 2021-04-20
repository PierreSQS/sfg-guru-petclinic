package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OwnerMapService extends AbstractMapService<Owner,Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }


    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner != null) {
            Set<Pet> pets = owner.getPets();
            if (pets != null) {
                pets.forEach(pet -> {
                    PetType petType = pet.getPetType();
                    if (petType != null) {
                        if (petType.getId() == null) {
                            pet.setPetType(petTypeService.save(petType));
                        }
                    } else {
                        throw new RuntimeException("Pet Type required!");
                    }

                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        // Not necessary, since Pet ID set in line above
                        // but just to be sure it happened!!!
                        pet.setId(savedPet.getId());
                    }
                });
            }
            log.info("##### saved Owner: {}:",owner);
            return super.save(owner);
        } else {
            return null;
        }

    }

    @Override
    public List<Owner> findByLastName(Owner owner) {
        return super.findAll().stream()
                .sorted()
                .filter(owner1 -> owner1.getLastName().equals(owner.getLastName()))
                .collect(Collectors.toList());
    }
}
