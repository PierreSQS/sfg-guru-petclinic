package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
public class SpecialitySDJpaService implements SpecialityService {
    private final SpecialityRepository specialityRepo;

    public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepo = specialityRepository;
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepo.findById(id).orElse(null);
    }

    @Override
    public List<Speciality> findAll() {
        return (List<Speciality>) specialityRepo.findAll();
    }

    @Override
    public Speciality save(Speciality speciality) {
        return specialityRepo.save(speciality);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepo.deleteById(id);
    }

    @Override
    public void delete(Speciality speciality) {
        specialityRepo.delete(speciality);
    }
}
