package com.atypon.literatumproject.webadmintool.services;


import com.atypon.literatumproject.webadmintool.form.UserCreateForm;
import com.atypon.literatumproject.webadmintool.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUserById(long id);

    public Optional<User> getUserByEmail(String email);

    public List<User> getAllUsers();

    public User createUser(UserCreateForm form);

    public void removeUser(Long id);

}
