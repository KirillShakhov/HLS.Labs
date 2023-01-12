package ru.itmo.hls.lab1.authorization.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.hls.dto.PageDto;
import ru.itmo.hls.lab1.authorization.dto.UserDto;
import ru.itmo.hls.lab1.authorization.entity.Role;
import ru.itmo.hls.lab1.authorization.entity.User;
import ru.itmo.hls.lab1.authorization.exceptions.AlreadyExistsException;
import ru.itmo.hls.lab1.authorization.exceptions.NotFoundException;
import ru.itmo.hls.lab1.authorization.exceptions.PageNotFoundException;
import ru.itmo.hls.lab1.authorization.repository.CustomizedUserCrudRepository;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserDataService {
    private final CustomizedUserCrudRepository customizedUserCrudRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(UserDto userDto) {
        log.info("registering user {}", userDto.getFirstName());
        if(customizedUserCrudRepository.existsByUsername(userDto.getUsername())) {
            log.warn("username {} already exists.", userDto.getUsername());
            throw new NotFoundException(
                    String.format("username %s already exists", userDto.getUsername()));
        }
        if(customizedUserCrudRepository.existsByEmail(userDto.getEmail())) {
            log.warn("email {} already exists.", userDto.getEmail());

            throw new AlreadyExistsException(
                    String.format("email %s already exists", userDto.getEmail()));
        }
        var user = User.builder().username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .firstName(userDto.getFirstName())
                .lastName(userDto.getSecondName())
                .email(userDto.getEmail())
                .role(Role.USER)
                .build();
        customizedUserCrudRepository.save(user);
    }

    public User getByUsername(String username) {
        return customizedUserCrudRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public PageDto getUsers(@NonNull Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 50);
        Page<User> patient = customizedUserCrudRepository.findAll(pageable);

        if (patient.isEmpty()) {
            throw new PageNotFoundException("Page not found");
        }

        return PageDto.builder()
                .items(patient.getContent())
                .hasMore(patient.hasNext())
                .build();
    }

    public void changeRole(String username, Role role) {
        var userOptional = customizedUserCrudRepository.findByUsername(username);
        if(userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        var user = userOptional.get();
        user.setRole(role);
        customizedUserCrudRepository.save(user);
    }
}

