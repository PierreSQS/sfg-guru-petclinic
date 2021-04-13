package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long,T> map = new HashMap<>();

    List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
                map.put(object.getId(), object);
            }
        } else {
            throw new RuntimeException("Object kann not be null!!");
        }

        return object;
    }

    void delete(T object) {
        map.entrySet().removeIf(entity -> entity.getValue().equals(object));
    }

    void deleteById(ID id) {
        map.remove(id);
    }
    
    private Long getNextId() {
        if (map.isEmpty()) return 1L;
        return Collections.max(map.keySet()) + 1;
    }
}
