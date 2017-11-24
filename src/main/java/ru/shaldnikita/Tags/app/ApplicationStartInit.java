package ru.shaldnikita.Tags.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.shaldnikita.Tags.backend.model.User;
import ru.shaldnikita.Tags.backend.model.dict.Role;
import ru.shaldnikita.Tags.backend.service.UserService;

@Component
public class ApplicationStartInit implements ApplicationRunner,HasLogger {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        getLogger().info("init starting data");

        User user = new User();
        user.setEmail("admin@tags.ru");
        user.setLogin("admin");
        user.setPassword(encoder.encode("admin1"));
        user.setRole(Role.ADMIN);

        userService.save(user);

    }
}
