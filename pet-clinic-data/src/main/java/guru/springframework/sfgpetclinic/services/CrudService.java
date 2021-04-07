package guru.springframework.sfgpetclinic.services;

import java.util.List;

public interface CrudService<T, Long> {
    T findById(Long id);

    List<T> findAll();

    T save(T object);

    void deleteById(Long id);

    void delete(T object);

}
