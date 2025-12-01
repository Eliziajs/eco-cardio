package domain.ports.portIn.service;

import domain.model.User;
import domain.ports.portOut.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Transactional
    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserByEmail (String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> getUserbyCpf(String cpf){
        return userRepository.findByCpf(cpf);
    }

    @Transactional
    public User updateUser (User user){
        return userRepository.update(user);
    }

    @Transactional
    public void deleteUser(Long id){
        userRepository.findByid(id).ifPresent(user -> userRepository.delete(user));
    }

}
