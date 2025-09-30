package infrastructure.output.persistence;


import domain.model.Paciente;
import domain.repository.PacienteRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import java.util.List;
import java.util.Optional;

public class PacienteRepositoryImpl implements PacienteRepository, PanacheRepository {


    @Override
    public Paciente save(Paciente paciente) {
       persist(paciente);
       return paciente;
    }

    @Override
    public Paciente update(Paciente paciente) {
        return getEntityManager().merge(paciente);
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return Optional.ofNullable((Paciente) find("id", id));
    }

    @Override
    public void delete(Paciente paciente) {
        deleteById(paciente.getId());

    }

    @Override
    public List<Optional<Paciente>> findAll1() {
        return listAll(Sort.by("name"));
    }
}
