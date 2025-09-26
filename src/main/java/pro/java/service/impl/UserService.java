package pro.java.service.impl;

import org.springframework.stereotype.Service;
import pro.java.dto.User;
import pro.java.repository.UserRepository;
import pro.java.service.IUserService;

import java.util.Collection;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(Long id, String name) {
        return userRepository.create(id, name);
    }

    @Override
    public User update(Long id, String name) {
        return userRepository.update(id, name);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.getAllUsers();
    }
}
