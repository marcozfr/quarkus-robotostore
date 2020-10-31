package org.example.sample.users.service;

import io.smallrye.mutiny.Uni;
import org.example.sample.users.model.User;

public interface UsersService {

    Uni<User> createUser(User user);

    Uni<User> getUserByUsername(String username);
}
