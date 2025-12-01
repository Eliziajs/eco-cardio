package application.dtos.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String name,

        @Size(min = 2, max = 100, message = "Sobrenome deve ter entre 2 e 100 caracteres")
        String lastName,

        String genero,

        @Size(min = 10, max = 15, message = "Telefone deve ter entre 10 e 15 caracteres")
        String telefone,

        @Email(message = "Email deve ser válido")
        String email,

        String crm
) {
}
