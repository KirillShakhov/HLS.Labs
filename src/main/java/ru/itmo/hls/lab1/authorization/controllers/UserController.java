package ru.itmo.hls.lab1.authorization.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.lab1.authorization.dto.UserDto;
import ru.itmo.hls.lab1.authorization.entity.Role;
import ru.itmo.hls.lab1.authorization.entity.User;
import ru.itmo.hls.lab1.authorization.services.UserDataService;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDataService userDataService;

    @PostMapping("/change-role")
    public ResponseEntity<String> makeUser(@RequestBody String username) {
        userDataService.changeRole(username, Role.SELLER);
        return ResponseEntity.ok("Successfully made a new user");
    }

    @PostMapping("/add")
    public ResponseEntity<String> registerNewUser(@Valid @RequestBody UserDto userDto) {
        userDataService.registerUser(userDto);
        return ResponseEntity.ok("Successfully added a user");
    }

    @GetMapping("/find/{number}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userDataService.getByUsername(username));
    }

    @GetMapping("/page/{number}")
    public ResponseEntity<PageDto> getAllUsers(@PathVariable Integer number) {
        return ResponseEntity.ok(userDataService.getUsers(number));
    }
}
