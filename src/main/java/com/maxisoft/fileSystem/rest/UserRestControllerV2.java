package com.maxisoft.fileSystem.rest;

import com.maxisoft.fileSystem.dto.UserDTO;
import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.models.User;
import com.maxisoft.fileSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/FileSystem/V2/users")
public class UserRestControllerV2 {
    private final UserService userService;

    @Autowired
    public UserRestControllerV2(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTO = userService.getAll().stream().map(UserDTO::convertUserToDTO).collect(Collectors.toList());
        if (userDTO.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("user_id") Long userId) {
        User user = userService.getById(userId);
        UserDTO userDTO = UserDTO.convertUserToDTO(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{user_id}/update")
    public ResponseEntity<User> updateUser(@PathVariable("user_id") Long userId, @RequestBody @Valid User user, BindingResult bindingResult) {
        user.setId(userId);
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{user_id}/delete")
    public ResponseEntity<User> deleteUser(@PathVariable("user_id") Long userId) {
        User user = userService.getById(userId);
        user.setStatus(Status.DELETED);
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
