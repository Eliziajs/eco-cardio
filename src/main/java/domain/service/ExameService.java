package domain.service;

import domain.model.Exame;
import domain.repository.ExameRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class ExameService {

    @Inject
    ExameRepository exameRepositoy;

    @Transactional
    Exame create(Exame exame) {
        return exameRepositoy.save(exame);
    }

    Optional<Exame> getFindById(Long id) {
        return exameRepositoy.findById(id);
    }

    @Transactional
    Exame updateExame(Exame exame) {
        return exameRepositoy.update(exame);
    }

    @Transactional
    void deleteExame(Long id) {
        exameRepositoy.findById(id).ifPresent(exame -> exameRepositoy.delete(exame));
    }
}
