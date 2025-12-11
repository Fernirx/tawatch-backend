package vn.fernirx.tawatch.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fernirx.tawatch.user.entity.User;
import vn.fernirx.tawatch.user.entity.UserProfile;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    // Tìm profile theo user
    Optional<UserProfile> findByUser(User user);

    // Tìm profile theo userId
    Optional<UserProfile> findByUserId(Long userId);

    // Tìm profile theo số điện thoại
    Optional<UserProfile> findByPhone(String phone);

    // Kiểm tra số điện thoại đã tồn tại chưa
    boolean existsByPhone(String phone);

    // Tìm kiếm profile theo tên (có phân trang)
    Page<UserProfile> findByFirstNameContainingIgnoreCase(String firstName, Pageable pageable);
    Page<UserProfile> findByLastNameContainingIgnoreCase(String lastName, Pageable pageable);

    // Tìm kiếm theo tên đầy đủ (có phân trang)
    Page<UserProfile> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);

    // Đếm số profile
    long count();
}
