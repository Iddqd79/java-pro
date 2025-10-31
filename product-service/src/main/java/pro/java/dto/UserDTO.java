package pro.java.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private Integer age;
    private Set<Long> productsId = new LinkedHashSet<>();
}
