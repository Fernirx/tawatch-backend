package vn.fernirx.tawatch.user.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.fernirx.tawatch.user.entity.Address;
import vn.fernirx.tawatch.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Tìm địa chỉ theo user (có phân trang)
    Page<Address> findByUser(User user, Pageable pageable);

    // Tìm địa chỉ theo userId (có phân trang)
    Page<Address> findByUserId(Long userId, Pageable pageable);

    // Tìm địa chỉ mặc định của user
    Optional<Address> findByUserIdAndIsDefault(Long userId, boolean isDefault);

    // Lấy danh sách địa chỉ của user (không phân trang)
    List<Address> findByUserId(Long userId);

    // Tìm kiếm địa chỉ theo tên người nhận (có phân trang)
    Page<Address> findByRecipientNameContainingIgnoreCase(String recipientName, Pageable pageable);

    // Tìm kiếm địa chỉ theo thành phố (có phân trang)
    Page<Address> findByCityContainingIgnoreCase(String city, Pageable pageable);

    // Đếm số địa chỉ của user
    long countByUserId(Long userId);

    // Kiểm tra user có địa chỉ nào không
    boolean existsByUserId(Long userId);
}
