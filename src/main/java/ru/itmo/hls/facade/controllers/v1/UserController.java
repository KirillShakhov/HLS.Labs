package ru.itmo.hls.facade.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itmo.hls.dto.CredentialsDto;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.dto.TokenDto;
import ru.itmo.hls.dto.UserDto;
import ru.itmo.hls.facade.client.AuthClient;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthClient authClient;

    @PostMapping("/add")
    String registerNewUser(@Valid @RequestBody UserDto userDto){
        return authClient.registerNewUser(userDto);
    }

    @PostMapping("/change-role")
    String changeRole(@RequestAttribute String username){
        return authClient.changeRole(username);
    }

    @GetMapping("/page/{number}")
    PageDto getAllUsers(@PathVariable Integer number){
        return authClient.getAllUsers(number);
    }
}
