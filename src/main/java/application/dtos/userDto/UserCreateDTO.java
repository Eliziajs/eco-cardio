package application.dtos.userDto;

/**
 * Data Transfer Object (DTO) for creating a new User.
 * This record defines the structure required for user creation data,
 * including validation annotations for each field.
 */
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

// Name field: mandatory, must be between 2 and 100 characters
public record UserCreateDTO(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
        String name,

        @NotBlank(message = "Sobrenome é obrigatório")
        @Size(min = 2, max = 100, message = "Sobrenome deve ter entre 2 e 100 caracteres")
        String lastName,

        String genero,

        @Size(min = 10, max = 15, message = "Telefone deve ter entre 10 e 15 caracteres")
        String telefone,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String password,

        String crm,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,

        @NotNull(message = "Data é obrigatória")
        LocalDateTime data


) {}
