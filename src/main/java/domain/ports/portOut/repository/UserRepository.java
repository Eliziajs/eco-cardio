package domain.ports.portOut.repository;

import domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {


    Optional<User> findByid(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);
    Optional<User> findByCrm(String crm);
    User save (User save);
    void delete(User User);
    User update (User user);

}
