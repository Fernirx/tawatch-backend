package vn.fernirx.tawatch.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "recipient_name", nullable = false, length = 200)
    private String recipientName;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "street", nullable = false, length = 255)
    private String street;

    @Column(name = "ward", nullable = false, length = 100)
    private String ward;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "postal_code", length = 6)
    private String postalCode;

    @Column(name = "country", nullable = false, length = 100)
    private String country = "Việt Nam";

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public String getFullAddress() {
        return String.format("%s, %s, %s, %s", street, ward, city, country);
    }

    public String getFormattedAddress() {
        StringBuilder sb = new StringBuilder();
        sb.append(recipientName).append("\n");
        sb.append(phone).append("\n");
        sb.append(street).append("\n");
        sb.append(ward).append(", ").append(city);
        if (postalCode != null && !postalCode.trim().isEmpty()) {
            sb.append(" - ").append(postalCode);
        }
        sb.append("\n").append(country);
        return sb.toString();
    }
}
