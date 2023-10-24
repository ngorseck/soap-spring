package com.spring.training.service;

import com.spring.training.entity.UserEntity;
import com.spring.training.mapping.UserMapper;
import com.spring.training.model.UserPrincipal;
import com.spring.training.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticationService implements UserDetailsService {

    final UserRepository repository;
    final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = repository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new UserPrincipal(mapper.toUser(entity));
    }
}
