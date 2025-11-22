package infrastructure.input.rest;


import domain.model.User;
import application.portIn.UserRepository;
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
    UserRepository userRepository;

    @POST
    public Response createUser(User user) {
        try {
            // Verificar se já existe usuário com o mesmo email ou CPF
            if (userRepository.findByEmail(user.getEmail()).isPresent()) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("Já existe um usuário com este email")
                        .build();
            }

            if (userRepository.findByCpf(user.getCpf()).isPresent()) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("Já existe um usuário com este CPF")
                        .build();
            }

            User createdUser = userRepository.save(user);
            return Response.status(Response.Status.CREATED)
                    .entity(createdUser)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao criar usuário: " + e.getMessage())
                    .build();
        }
    }

    @GET
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
    }
}


