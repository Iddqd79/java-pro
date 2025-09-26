package pro.java.service;

import pro.java.dto.User;

import java.util.Collection;

public interface IUserService {
    User create(Long id, String name);


    User upadate(Long id, String name);

    void delete(Long id);

    User findById(Long id);

    Collection<User> findAll();

}
