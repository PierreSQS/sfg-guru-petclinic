package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.PetType;

import java.util.List;

public interface PetTypeService extends CrudService<PetType, Long> {
    List<PetType> findByName(String text);
}
