package ru.shaldnikita.Tags.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.shaldnikita.Tags.backend.model.Tag;
import ru.shaldnikita.Tags.backend.model.User;
import ru.shaldnikita.Tags.backend.model.dict.Role;
import ru.shaldnikita.Tags.backend.repositories.TagRepository;
import ru.shaldnikita.Tags.backend.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ApplicationStartInit implements ApplicationRunner,HasLogger {

    @Autowired
    UserService userService;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        getLogger().info("init starting data");

        User user = new User();
        user.setEmail("admin@tags.ru");
        user.setLogin("admin");
        user.setPassword(encoder.encode("admin1"));
        user.setRole(Role.ADMIN);

        user = userService.save(user);
        List<Tag> tags = new ArrayList<>();

        for (int i=0;i<10;i++){
            Tag tag = new Tag();
            tag.setAuthor(user);

            tag.setLatitude(55.85+ Double.valueOf(i)/1000);
            tag.setLongitude(37.61+Double.valueOf(i)/1000);
            tag.setName("tag "+i);
            tag.setCategories(Arrays.asList("category"+i,"category"+i+i));

            tags.add(tag);
        }
        tagRepository.save(tags);

        getLogger().info(Arrays.toString(tagRepository.getAllByLatitudeBetweenAndLongitudeBetweenAndDelFalse(55.851,55.855,37.612,37.614).toArray()));
    }}