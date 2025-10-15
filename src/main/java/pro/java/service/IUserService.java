package pro.java.service;

import pro.java.dto.ProductDTO;
import pro.java.dto.UserDTO;

import java.util.Collection;
import java.util.Optional;

public interface IUserService {
    UserDTO create(UserDTO user);

    void delete(UserDTO user);

    Optional<UserDTO> findById(Long id);

    Collection<UserDTO> findAll();

    void addProduct(ProductDTO product);

    void removeProduct(ProductDTO product);

    Collection<ProductDTO> getAllProductsByUserId(Long id);

}
