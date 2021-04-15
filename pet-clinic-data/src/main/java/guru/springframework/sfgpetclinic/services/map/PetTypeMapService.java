package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService{

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(PetType petype) {
        super.delete(petype);
    }

    @Override
    public void deleteById(Long id) {
        super.findById(id);
    }

    @Override
    public PetType save(PetType petype) {
        return super.save(petype);
    }
}
