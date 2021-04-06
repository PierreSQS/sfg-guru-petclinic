package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Vet;

import java.util.List;

public interface VetService {

    Vet findById(Long id);

    List<Vet> findAll();

    Vet save(Pet vet);
}
