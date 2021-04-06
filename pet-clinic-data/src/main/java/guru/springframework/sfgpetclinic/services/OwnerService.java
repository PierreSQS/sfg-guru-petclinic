package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService {
    List<Owner> findByLastName(Owner owner);

    Owner findById(Long id);

    List<Owner> findAll();

    Owner save(Owner owner);
}
