package infrastructure.output.persistence;

import domain.model.User;
import domain.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepository<User> {


    @Override
    public List<User> findAll1() {
        return listAll(Sort.by("name"));
    }

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
