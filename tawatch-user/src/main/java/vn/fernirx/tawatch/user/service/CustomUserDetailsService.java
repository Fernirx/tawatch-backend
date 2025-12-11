package vn.fernirx.tawatch.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.fernirx.tawatch.common.exception.ResourceNotFoundException;
import vn.fernirx.tawatch.user.entity.User;
import vn.fernirx.tawatch.user.mapper.UserMapper;
import vn.fernirx.tawatch.user.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        email
                ));
        return userMapper.toCustomUserDetails(user);
    }
}