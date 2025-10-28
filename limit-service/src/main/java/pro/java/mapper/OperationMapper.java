package pro.java.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pro.java.dto.OperationDTO;
import pro.java.entity.Operation;

@Component
@RequiredArgsConstructor
public class OperationMapper {
    private final ModelMapper modelMapper;

    public Operation convertToEntity(OperationDTO source) {
        return modelMapper.map(source, Operation.class);
    }

    public OperationDTO convertToDTO(Operation source) {
        return modelMapper.map(source, OperationDTO.class);
    }

}
