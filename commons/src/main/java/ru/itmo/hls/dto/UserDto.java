package ru.itmo.hls.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "firstName may not be null")
    @Size(min = 4, max = 64, message = "First name '${validatedValue}' must be between {min} and {max} characters long")
    String firstName;

    @NotNull(message = "secondName may not be null")
    @Size(min = 4, max = 64, message = "Second name '${validatedValue}' must be between {min} and {max} characters long")
    String secondName;

    @NotNull(message = "username may not be null")
    @Size(min = 4, max = 64, message = "username '${validatedValue}' must be between {min} and {max} characters long")
    String username;

    @NotNull(message = "email may not be null")
    @Email(message = "email '${validatedValue}' is not valid")
    String email;

    @NotNull(message = "password may not be null")
    @Size(min = 4, max = 64, message = "password '${validatedValue}' must be between {min} and {max} characters long")
    String password;
}
