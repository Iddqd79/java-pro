package pro.java.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.java.dto.ProductDTO;
import pro.java.dto.UserDTO;
import pro.java.entity.Product;
import pro.java.entity.User;
import pro.java.mapper.ProductMapper;
import pro.java.mapper.UserMapper;
import pro.java.repository.ProductRepository;
import pro.java.repository.UserRepository;
import pro.java.service.IUserService;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final UserMapper userMapper;
    private final ProductMapper productMapper;


    @Override
    public UserDTO create(UserDTO dto) {
        User user = userMapper.convertToEntity(dto);
        User storedUser = userRepository.saveAndFlush(user);
        return userMapper.convertToDTO(storedUser);
    }


    @Override
    public void delete(UserDTO dto) {
        User user = userMapper.convertToEntity(dto);
        userRepository.delete(user);
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(userMapper::convertToDTO);
    }

    @Override
    public Collection<UserDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::convertToDTO).toList();
    }

    @Override
    public void addProduct(ProductDTO dto) {
        Product product = productMapper.convertToEntity(dto);
        productRepository.saveAndFlush(product);
    }

    @Override
    public void removeProduct(ProductDTO dto) {
        Product product = productMapper.convertToEntity(dto);
        productRepository.delete(product);
    }

}
