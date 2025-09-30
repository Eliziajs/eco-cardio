package domain.service;

import domain.model.Paciente;
import domain.repository.PacienteRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class PacienteService {

    @Inject
    PacienteRepository pacienteRepository;

    @Transactional
    Paciente create (Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    List<Optional<Paciente>> findAll (){
        return pacienteRepository.findAll1();
    }

   @Transactional
   Paciente updatepaciente(Paciente paciente){
        return pacienteRepository.update(paciente);
   }

   Optional<Paciente> findByIdPaciente (Long id){
        return pacienteRepository.findById(id);
   }
   @Transactional
   void deletePaciente(Long id){
      pacienteRepository.findById(id)
              .ifPresent(paciente -> pacienteRepository.delete(paciente));

   }
}
