package vn.fernirx.tawatch.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "guest_token", nullable = false, length = 64, unique = true)
    private String guestToken;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_active", nullable = false)
    private LocalDateTime lastActive;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @PrePersist
    @PreUpdate
    public void updateLastActive() {
        this.lastActive = LocalDateTime.now();
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }

    public void extendExpiration(int days) {
        this.expiresAt = LocalDateTime.now().plusDays(days);
    }

    public long getDaysUntilExpiration() {
        return java.time.Duration.between(LocalDateTime.now(), expiresAt).toDays();
    }
}