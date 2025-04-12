package com.officetech.officetech.API.iam.infrastructure.authorization.sfs.services;

import com.officetech.officetech.API.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.officetech.officetech.API.iam.infrastructure.persistence.jpa.repositories.UserIAMRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserIAMRepository userRepository;

    public UserDetailsServiceImpl(UserIAMRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}

