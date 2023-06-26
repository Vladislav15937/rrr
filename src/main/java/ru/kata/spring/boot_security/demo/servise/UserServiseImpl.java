package ru.kata.spring.boot_security.demo.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepositories;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiseImpl implements UserServise {

    private final UserRepositories userRepositories;
    private final PasswordEncoder passwordEncoder;

    public UserServiseImpl(UserRepositories userRepository, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUserById(Long id) {
        return userRepositories.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id " + id));
    }

    @Override
    public List<User> allUsers() {
        return userRepositories.findAll();
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (userRepositories.findById(id).isPresent()) {
            userRepositories.deleteById(id);
        }
    }

    @Transactional
    @Override
    public User addUsers(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepositories.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        if (!user.getPassword().equals(findUserById(user.getId()).getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepositories.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepositories.findUserByName(name);
    }
}
