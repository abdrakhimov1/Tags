package ru.shaldnikita.Tags.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shaldnikita.Tags.backend.model.User;
import ru.shaldnikita.Tags.backend.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService extends CrudService<User> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return getRepository().findByEmail(email);
    }


    @Override
    protected UserRepository getRepository() {
        return userRepository;
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
        return 0;
    }

    @Override
    public User save(User entity) {
        return super.save(entity);
    }
}
