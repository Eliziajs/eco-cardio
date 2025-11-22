package application.portIn;

import domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll1();
    Optional<User> findByid(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);
    User save (User save);
    void delete(User User);
    User update (User user);

}
