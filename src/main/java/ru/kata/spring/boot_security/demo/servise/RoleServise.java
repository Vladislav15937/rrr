package ru.kata.spring.boot_security.demo.servise;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleServise {

    public List<Role> getAllRoles();
}
