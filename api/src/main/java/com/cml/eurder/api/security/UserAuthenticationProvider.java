package com.cml.eurder.api.security;

import com.cml.eurder.domain.user.Feature;
import com.cml.eurder.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static com.google.common.collect.Lists.newArrayList;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationService authenticationService;

    @Autowired
    public UserAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = authenticationService.getUser(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        if(user != null){
            return new UsernamePasswordAuthenticationToken(
                    user.getFirstName(),
                    user.getPassWord(),
                    rolesToGrantedAuthorities(Feature.getFeaturesForRoles(newArrayList(user.getRole().toString()))));
        }
        throw new BadCredentialsException("The provided credentials were invalid.");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<Feature> features) {
        return features.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
