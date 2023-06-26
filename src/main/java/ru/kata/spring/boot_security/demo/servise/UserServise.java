package ru.kata.spring.boot_security.demo.servise;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServise {

    public User findUserById(Long id);

    public List<User> allUsers();

    public void deleteUser(Long id);

    public User addUsers(User user);

    public void update(User user);

    public User findByName(String name);
}
