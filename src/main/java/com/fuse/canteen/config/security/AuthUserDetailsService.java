package com.fuse.canteen.config.security;

import com.fuse.canteen.constants.Role;
import com.fuse.canteen.repo.UserRepository;
import com.fuse.canteen.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
   
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        User user = userRepository.findByUserNameOrEmail(userName).
                orElseThrow(() -> new UsernameNotFoundException("Invalid Username or Password."));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPassword(),
                getAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            Integer role) {
        return Collections.singleton(new SimpleGrantedAuthority(Role.getByKey(role).getValue()));
    }

}