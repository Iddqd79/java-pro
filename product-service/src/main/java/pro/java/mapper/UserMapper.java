package pro.java.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pro.java.dto.UserDTO;
import pro.java.entity.User;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public User convertToEntity(UserDTO source) {
        return modelMapper.map(source, User.class);
    }

    public UserDTO convertToDTO(User source) {
        return modelMapper.map(source, UserDTO.class);
    }

}
