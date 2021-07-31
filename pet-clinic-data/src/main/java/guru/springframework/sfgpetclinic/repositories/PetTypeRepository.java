package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
    List<PetType> findByName(String text);
}
