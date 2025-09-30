package domain.repository;

import domain.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository {

    Paciente save (Paciente paciente);
    Paciente update (Paciente paciente);
    Optional<Paciente> findById(Long id);
    void delete(Paciente paciente);
    List<Optional<Paciente>> findAll1();

}
