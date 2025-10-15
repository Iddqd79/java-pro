package pro.java.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pro.java.dto.UserDTO;
import pro.java.service.IUserService;

import java.util.Collection;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UsersController {
    private final IUserService userService;

    @PostMapping
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(Long id) {
        Optional<UserDTO> userDto = userService.findById(id);
        if (userDto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(userDto.get());
        return ResponseEntity.ok().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> get(@PathVariable Long id) {
        return userService.findById(id).map(userDTO -> ResponseEntity.ok().body(userDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Collection<UserDTO> getAll() {
        return userService.findAll();
    }

}
