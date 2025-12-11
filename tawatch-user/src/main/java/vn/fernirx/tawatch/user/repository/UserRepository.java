package vn.fernirx.tawatch.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fernirx.tawatch.common.enums.AuthProvider;
import vn.fernirx.tawatch.common.enums.UserRole;
import vn.fernirx.tawatch.user.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm user theo email
    Optional<User> findByEmail(String email);

    // Kiểm tra email đã tồn tại chưa
    boolean existsByEmail(String email);

    // Tìm user theo provider và providerId (cho OAuth2)
    Optional<User> findByProviderAndProviderId(AuthProvider provider, String providerId);

    // Tìm user theo role (có phân trang)
    Page<User> findByRole(UserRole role, Pageable pageable);

    // Tìm user đang active (có phân trang)
    Page<User> findByIsActive(boolean isActive, Pageable pageable);

    // Tìm user đã xác thực email (có phân trang)
    Page<User> findByIsVerified(boolean isVerified, Pageable pageable);

    // Tìm kiếm user theo email (có phân trang)
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);

    // Đếm số user theo role
    long countByRole(UserRole role);
}