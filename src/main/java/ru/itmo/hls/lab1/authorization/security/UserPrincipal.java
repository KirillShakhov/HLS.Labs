package ru.itmo.hls.lab1.authorization.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itmo.hls.lab1.authorization.entity.User;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;


@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final User credentials;

    /**
     * Returns the authorities granted to the user. cannot return null.
     *
     * @return the authorities, sorted by natural key (never null)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(credentials.getRole().name()));
    }

    /**
     * Returns the id used to authenticate the user. Cannot return null.
     *
     * @return the id (never null)
     */
    public UUID getId() {
        return credentials.getId();
    }

    /**
     * Returns the username used to authenticate the user. Cannot return null.
     *
     * @return the username (never null)
     */
    @Override
    public String getUsername() {
        return credentials.getUsername();
    }

    /**
     * Returns the password user to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return credentials.getPassword();
    }

    /**
     * Indicates whether the user's account has expired. An expired
     * account cannot be authenticated.
     *
     * @return true if the user's account is valid (ie non-expired),
     *         false if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked
     * user cannot be authenticated.
     *
     * @return true if the user is not locked, false otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     * Expired credentials prevent authentication.
     *
     * @return true if the user's credentials are valid (ie non-expired),
     *         false if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled
     * user cannot be authenticated.
     *
     * @return true if the user is enabled, false otherwise
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
