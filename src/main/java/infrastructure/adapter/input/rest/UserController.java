package infrastructure.adapter.input.rest;


import application.dtos.userDto.UserCreateDTO;
import application.dtos.userDto.UserResponseDTO;
import application.useCase.CriarNovoUsuarioUseCase;
import domain.model.User;
import domain.ports.portOut.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserController {

    @Inject
    CriarNovoUsuarioUseCase useCase;

    @POST
    public Response createUser(UserCreateDTO userDTO) {

        try {
            UserResponseDTO createdUser = useCase.CriarNovoUsuarioUseCase(userDTO);
            return Response.status(Response.Status.CREATED)
                    .entity(createdUser)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao criar usuário: " + e.getMessage())
                    .build();
        }
    }

  /*  @GET
    @Path("/email/{email}")
    public Response getUserByEmail(@PathParam("email") String email) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            return user.map(u -> Response.ok(u).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND)
                            .entity("Usuário não encontrado")
                            .build());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar usuário: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/cpf/{cpf}")
    public Response getUserByCpf(@PathParam("cpf") String cpf) {
        try {
            Optional<User> user = userRepository.findByCpf(cpf);
            return user.map(u -> Response.ok(u).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND)
                            .entity("Usuário não encontrado")
                            .build());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar usuário: " + e.getMessage())
                    .build();
        }
    }


    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") Long id) {
        try {
            Optional<User> user = userRepository.findByid(id);
            return user.map(u -> Response.ok(u).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND)
                            .entity("Usuário não encontrado")
                            .build());
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar usuário: " + e.getMessage())
                    .build();
        }
    }*/
}


