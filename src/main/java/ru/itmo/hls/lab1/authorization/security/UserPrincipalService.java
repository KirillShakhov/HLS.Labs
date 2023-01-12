package ru.itmo.hls.lab1.authorization.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itmo.hls.lab1.authorization.repository.CustomizedUserCrudRepository;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final CustomizedUserCrudRepository credentialsRepository;

    /**
     * Locates the user based on the username. In the actual
     * implementation, the search may be case-insensitive, or
     * case-insensitive depending on how the implementation
     * instance is configured. In this case, the UserDetails
     * object that comes back may have a username that is of
     * a different case than what was actually requested...
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never null)
     * @throws UsernameNotFoundException if the user could not be found or
     *                                   the user has no GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credentialsRepository.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * @param id the id identifying the user whose data is required.
     * @return a fully populated user record (never null)
     * @throws UsernameNotFoundException if the user could not be found or
     *                                   the user has no GrantedAuthority
     */
    public UserDetails loadUserById(UUID id) throws UsernameNotFoundException {
        return credentialsRepository.findById(id)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
