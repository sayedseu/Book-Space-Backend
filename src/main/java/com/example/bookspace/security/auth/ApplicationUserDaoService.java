package com.example.bookspace.security.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.bookspace.security.ApplicationUserRole.ADMIN;

@Repository(value = "admin")
public class ApplicationUserDaoService implements ApplicationUserDao{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return Lists.newArrayList(
                new ApplicationUser(
                        "admin",
                        passwordEncoder.encode("SAYEDhasan@77331100"),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_"+ADMIN.name())),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        "teamjupiter007",
                        passwordEncoder.encode("teamjupiter007"),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_"+ADMIN.name())),
                        true,
                        true,
                        true,
                        true
                )
        );
    }


}
