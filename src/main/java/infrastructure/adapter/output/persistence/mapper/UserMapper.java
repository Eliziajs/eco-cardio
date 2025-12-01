package infrastructure.adapter.output.persistence.mapper;

import application.dtos.userDto.UserCreateDTO;
import application.dtos.userDto.UserResponseDTO;
import application.dtos.userDto.UserUpdateDTO;
import domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setLastName(dto.lastName());
        user.setGenero(dto.genero());
        user.setTelefone(dto.telefone());
        user.setEmail(dto.email());
        user.setPassword(dto.password()); // Na prática, fazer hash
        user.setCrm(dto.crm());
        user.setCpf(dto.cpf());
        user.setData(dto.data());
        return user;
    }

    public void updateEntityFromDTO(UserUpdateDTO dto, User user) {
        if (dto.name() != null) user.setName(dto.name());
        if (dto.lastName() != null) user.setLastName(dto.lastName());
        if (dto.genero() != null) user.setGenero(dto.genero());
        if (dto.telefone() != null) user.setTelefone(dto.telefone());
        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.crm() != null) user.setCrm(dto.crm());
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getGenero(),
                user.getTelefone(),
                user.getEmail(),
                user.getCrm(),
                user.getCpf(),
                user.getData()
        );
    }
}
