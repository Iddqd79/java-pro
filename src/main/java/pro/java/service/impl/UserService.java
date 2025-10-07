package pro.java.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.java.dto.User;
import pro.java.repository.UserRepository;
import pro.java.service.IUserService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;


    @Override
    public User create(Long id, String name) {
        return userRepository.saveAndFlush(new User(id, name, -1));
    }

    @Override
    public User update(Long id, String name) {
        userRepository.updateName(name, id);
        return userRepository.getUserById(id);

    }

    @Override
    public void delete(Long id) {

        userRepository.deleteUserById(id);
    }

    @Override
    public User findById(Long id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

}
