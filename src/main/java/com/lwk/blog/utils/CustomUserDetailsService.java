package com.lwk.blog.utils;

import com.lwk.blog.pojo.Authorities;
import com.lwk.blog.pojo.Users;
import com.lwk.blog.service.IAuthoritiesService;
import com.lwk.blog.service.IUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author lwk
 * @date 2024-07-17 21:30:57
 * @deprecated 描述
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserService userService;
    private final IAuthoritiesService authoritiesService;

    public CustomUserDetailsService(IUserService userService, IAuthoritiesService authoritiesService) {
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userService.findByUsername(username).get();
        if (users == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Collection<? extends GrantedAuthority> authorities = authoritiesService.getAuthoritiesForUser(users.getUsername())
                .stream()
                .map(Authorities.class::cast)
                .collect(Collectors.toList());

        return new CustomUserDetails(users, authorities);
    }
}