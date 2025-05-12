package in.tech_camp.shopping_api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "username" ,nullable = false, length = 50)
  private String userName;

  @Column(nullable = false, length = 255)
  private String email;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(name = "phone_number", nullable = true, length = 20)
  private String phoneNumber;

  @Column(nullable = false, length = 10)
  private String postcode;

  @Column(nullable = false, length = 255)
  private String address;

  @Column(name = "icon_image", nullable = true, length = 255)
  private String iconImage;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "deleted_at")
  private LocalDateTime deletedAt;
}
