package vn.fernirx.tawatch.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.fernirx.tawatch.common.dto.PageResponse;
import vn.fernirx.tawatch.common.enums.UserRole;
import vn.fernirx.tawatch.common.exception.ResourceAlreadyExistsException;
import vn.fernirx.tawatch.common.exception.ResourceNotFoundException;
import vn.fernirx.tawatch.user.dto.request.UserRequest;
import vn.fernirx.tawatch.user.dto.request.UserUpdateRequest;
import vn.fernirx.tawatch.user.dto.response.UserResponse;
import vn.fernirx.tawatch.user.entity.User;
import vn.fernirx.tawatch.user.mapper.UserMapper;
import vn.fernirx.tawatch.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        log.info("Getting user by ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        log.info("Getting user by email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public PageResponse<UserResponse> getAllUsers(Pageable pageable) {
        log.info("Getting all users - pageable: {}", pageable);
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserResponse> responses = userPage.getContent().stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());

        return PageResponse.<UserResponse>builder()
                .content(responses)
                .page(userPage.getNumber())
                .size(userPage.getSize())
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .build();
    }

    @Transactional(readOnly = true)
    public PageResponse<UserResponse> searchUsersByEmail(String email, Pageable pageable) {
        log.info("Searching users by email - pageable: {}", pageable);
        Page<User> userPage = userRepository.findByEmailContainingIgnoreCase(email, pageable);
        List<UserResponse> responses = userPage.getContent().stream()
                .map(userMapper::toResponse)
                .toList();

        return PageResponse.<UserResponse>builder()
                .content(responses)
                .page(userPage.getNumber())
                .size(userPage.getSize())
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .build();
    }

    @Transactional(readOnly = true)
    public PageResponse<UserResponse> getUsersByRole(UserRole role, Pageable pageable) {
        log.info("Getting users by role - pageable: {}", pageable);
        Page<User> userPage = userRepository.findByRole(role, pageable);

        List<UserResponse> responses = userPage.getContent().stream()
                .map(userMapper::toResponse)
                .toList();

        return PageResponse.<UserResponse>builder()
                .content(responses)
                .page(userPage.getNumber())
                .size(userPage.getSize())
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .build();
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        log.info("Creating new user with email: {}", request.getEmail());
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("User");
        }
        User user = userMapper.toEntity(request);
        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }
        user.setIsActive(request.getIsActive() != null ? request.getIsActive() : true);
        user.setIsVerified(request.getIsVerified() != null ? request.getIsVerified() : false);
        user.setLoginAttempts(0);
        user.setLockedUntil(null);
        User savedUser = userRepository.save(user);
        log.info("User created with ID: {}", savedUser.getId());
        return userMapper.toResponse(savedUser);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        log.info("Updating user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));
        if (request.getEmail() != null &&
                !request.getEmail().equals(user.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceAlreadyExistsException("User");
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getPassword() != null && !request.getPassword().trim().isEmpty()) {
            user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        }

        if (request.getIsActive() != null) {
            user.setIsActive(request.getIsActive());
        }

        user = userRepository.save(user);
        log.info("User updated with ID: {}", user.getId());
        return userMapper.toResponse(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User");
        }

        userRepository.deleteById(id);
        log.info("User deleted with ID: {}", id);
    }

    @Transactional
    public UserResponse toggleUserStatus(Long id) {
        log.info("Toggling status for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));

        user.setIsActive(!user.getIsActive());
        user = userRepository.save(user);

        log.info("User status toggled. ID: {}, New Status: {}", id, user.getIsActive());
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse updateUserRole(Long id, UserRole newRole) {
        log.info("Updating role for user with ID: {} to {}", id, newRole);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));

        user.setRole(newRole);
        user = userRepository.save(user);

        log.info("User role updated. ID: {}, New Role: {}", id, newRole);
        return userMapper.toResponse(user);
    }

    @Transactional(readOnly = true)
    public long countUsers() {
        return userRepository.count();
    }

    @Transactional(readOnly = true)
    public long countUsersByRole(UserRole role) {
        return userRepository.countByRole(role);
    }

    @Transactional
    public UserResponse verifyEmail(Long id) {
        log.info("Verifying email for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));

        user.setIsVerified(true);
        user = userRepository.save(user);

        log.info("Email verified for user with ID: {}", id);
        return userMapper.toResponse(user);
    }

    @Transactional
    public void updateLastLogin(Long id) {
        log.debug("Updating last login for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }

    @Transactional
    public void resetLoginAttempts(Long id) {
        log.debug("Resetting login attempts for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));

        user.setLoginAttempts(0);
        user.setLockedUntil(null);
        userRepository.save(user);
    }

    @Transactional
    public void incrementLoginAttempts(Long id) {
        log.debug("Incrementing login attempts for user with ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User"
                ));

        int attempts = user.getLoginAttempts() + 1;
        user.setLoginAttempts(attempts);

        if (attempts >= 5) {
            user.setLockedUntil(LocalDateTime.now().plusMinutes(30));
        }

        userRepository.save(user);
    }
}