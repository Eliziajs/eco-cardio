package infrastructure.adapter.output.persistence.repositoryImp;

import domain.ports.portOut.repository.ExameRepository;
import domain.model.Exame;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ExameRepositoryAdapter implements ExameRepository, PanacheRepository {
    @Override
    public List<Optional<Exame>> findAll1() {
        return listAll(Sort.by("data"));
    }

    @Override
    public Optional<Exame> findById(Long id) {
        return Optional.ofNullable((Exame) find("id", id).firstResult());
    }

    @Override
    public Exame save(Exame exame) {
        persist(exame);
        return exame;
    }

    @Override
    public Exame update(Exame exame) {
        return getEntityManager().merge(exame);
    }

    @Override
    public void delete(Exame exame) {
        deleteById(exame.getId());

    }
}
