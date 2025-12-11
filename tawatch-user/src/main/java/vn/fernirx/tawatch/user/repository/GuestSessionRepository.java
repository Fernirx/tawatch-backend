package vn.fernirx.tawatch.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.fernirx.tawatch.user.entity.GuestSession;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface GuestSessionRepository extends JpaRepository<GuestSession, Long> {

    // Tìm session theo token
    Optional<GuestSession> findByGuestToken(String guestToken);

    // Kiểm tra token có tồn tại không
    boolean existsByGuestToken(String guestToken);

    // Lấy danh sách session (có phân trang)
    Page<GuestSession> findAll(Pageable pageable);

    // Lấy session còn hiệu lực (chưa hết hạn) - có phân trang
    @Query("SELECT g FROM GuestSession g WHERE g.expiresAt > :now")
    Page<GuestSession> findActiveSessions(@Param("now") LocalDateTime now, Pageable pageable);

    // Lấy session đã hết hạn - có phân trang
    @Query("SELECT g FROM GuestSession g WHERE g.expiresAt <= :now")
    Page<GuestSession> findExpiredSessions(@Param("now") LocalDateTime now, Pageable pageable);

    // Xóa session đã hết hạn
    @Modifying
    @Transactional
    @Query("DELETE FROM GuestSession g WHERE g.expiresAt <= :now")
    int deleteExpiredSessions(@Param("now") LocalDateTime now);

    // Cập nhật thời gian hoạt động cuối
    @Modifying
    @Transactional
    @Query("UPDATE GuestSession g SET g.lastActive = :now WHERE g.guestToken = :guestToken")
    void updateLastActive(@Param("guestToken") String guestToken, @Param("now") LocalDateTime now);

    // Đếm số session còn hiệu lực
    @Query("SELECT COUNT(g) FROM GuestSession g WHERE g.expiresAt > :now")
    long countActiveSessions(@Param("now") LocalDateTime now);
}
