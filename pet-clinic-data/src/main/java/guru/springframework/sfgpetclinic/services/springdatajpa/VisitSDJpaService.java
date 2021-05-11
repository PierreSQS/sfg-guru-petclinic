package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService implements VisitService {
    private final VisitRepository visitRepo;

    public VisitSDJpaService(VisitRepository visitRepo) {
        this.visitRepo = visitRepo;
    }

    @Override
    public Visit findById(Long id) {
        return visitRepo.findById(id).orElse(null);
    }

    @Override
    public List<Visit> findAll() {
        return (List<Visit>) visitRepo.findAll();
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepo.save(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepo.deleteById(id);
    }

    @Override
    public void delete(Visit visit) {
        visitRepo.delete(visit);
    }
}
