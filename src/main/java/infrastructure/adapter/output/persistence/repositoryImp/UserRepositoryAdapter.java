package infrastructure.adapter.output.persistence.repositoryImp;

import domain.model.User;


import domain.ports.portOut.repository.UserRepository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class UserRepositoryAdapter implements UserRepository, PanacheRepository<User> {

    @Override
    public Optional<User> findByid(Long id) {
        return Optional.ofNullable(find("id", id).firstResult());
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return find("email", email).firstResultOptional();

    }

    @Override
    public Optional<User> findByCpf(String cpf) {
        return find("cpf", cpf).firstResultOptional();
    }

    @Override
    public Optional<User> findByCrm(String crm) {
        return find("CRM", crm).firstResultOptional();
    }

    @Override
    public User save(User user) {
        persist(user);
        return user;
    }

    @Override
    public void delete(User user) {
       deleteById(user.getId());

    }

    @Override
    public User update(User user) {
        return getEntityManager().merge(user);
    }
}
