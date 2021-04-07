package guru.springframework.sfgpetclinic.services;

import java.util.List;

public interface CrudService<T, ID> {
    T findById(ID id);

    List<T> findAll();

    T save(T object);

    void deleteById(ID id);

    void delete(T object);

}
