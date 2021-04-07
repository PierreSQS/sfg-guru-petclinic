package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.CrudService;

import java.util.List;

public class OwnerMapService extends AbstractMapService<Owner,Long> implements CrudService<Owner, Long> {
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
        return super.save(owner.getId(), owner);
    }
}
