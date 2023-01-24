package com.maxisoft.fileSystem.security.jwt;

import com.maxisoft.fileSystem.models.Role;
import com.maxisoft.fileSystem.models.Status;
import com.maxisoft.fileSystem.models.User;
import com.maxisoft.fileSystem.security.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getStatus().equals(Status.NEW),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role userRole) {
        return List.of(new SimpleGrantedAuthority(userRole.getRoleName()));
    }
}
