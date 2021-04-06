package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Pet;

import java.util.List;

public interface PetService {

    Pet findById(Long id);

    List<Pet> findAll();

    Pet save(Pet pet);
}
