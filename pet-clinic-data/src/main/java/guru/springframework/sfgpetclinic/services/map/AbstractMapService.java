package guru.springframework.sfgpetclinic.services.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService<T, Long> {
    protected Map<Long,T> map = new HashMap<>();

    List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    T findById(Long id) {
        return map.get(id);
    }

    T save(Long id, T object) {
        map.put(id, object);
        return object;
    }

    void delete(T object) {
        map.entrySet().removeIf(entity -> entity.getValue().equals(object));
    }

    void deleteById(Long id) {
        map.remove(id);
    }
}
