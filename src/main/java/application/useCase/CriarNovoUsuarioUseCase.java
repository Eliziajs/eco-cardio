package application.useCase;

import application.dtos.userDto.UserCreateDTO;
import application.dtos.userDto.UserResponseDTO;
import domain.ports.portOut.repository.UserRepository;
import infrastructure.adapter.output.persistence.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;

@ApplicationScoped
public class CriarNovoUsuarioUseCase {

    @Inject
    UserRepository repository;
    @Inject
    UserMapper mapper;


    @Transactional
    public UserResponseDTO CriarNovoUsuarioUseCase(UserCreateDTO dto) {
        var user = mapper.toEntity(dto);

        validateBusinessRules(dto);
        //user.setPassword(passwordEncoder.encode(dto.password()));

        // Data de criação
        user.setData(LocalDateTime.now());
        repository.save(user);
        return mapper.toResponseDTO(user);

    }

    private void validateBusinessRules(UserCreateDTO dto) {
        // Verificar se email já existe
        if (repository.findByEmail(dto.email()).isPresent()) {
            throw new WebApplicationException(
                    "Email " + dto.email() + " já está cadastrado",
                    Response.Status.CONFLICT
            );
        }

        // Verificar se CPF já existe
        if (repository.findByCpf(dto.cpf()).isPresent()) {
            throw new WebApplicationException(
                    "CPF " + dto.cpf() + " já está cadastrado",
                    Response.Status.CONFLICT
            );
        }

        // Validação específica para CRM (se for médico)
        if (dto.crm() != null && !dto.crm().isBlank()) {
            if (repository.findByCrm(dto.crm()).isPresent()) {
                throw new WebApplicationException(
                        "CRM " + dto.crm() + " já está cadastrado",
                        Response.Status.CONFLICT
                );
            }
        }
    }


}
