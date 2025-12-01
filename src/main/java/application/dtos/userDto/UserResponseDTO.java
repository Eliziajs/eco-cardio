package application.dtos.userDto;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String lastName,
        String genero,
        String telefone,
        String email,
        String crm,
        String cpf,
        LocalDateTime data
) {
}
