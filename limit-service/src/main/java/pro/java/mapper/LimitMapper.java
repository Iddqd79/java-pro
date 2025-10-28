package pro.java.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pro.java.dto.LimitDTO;
import pro.java.entity.Limit;

@Component
@RequiredArgsConstructor
public class LimitMapper {
    private final ModelMapper modelMapper;

    public Limit convertToEntity(LimitDTO source) {
        return modelMapper.map(source, Limit.class);
    }

    public LimitDTO convertToDTO(Limit source) {
        return modelMapper.map(source, LimitDTO.class);
    }

}
