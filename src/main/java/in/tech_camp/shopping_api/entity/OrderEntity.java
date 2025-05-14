package in.tech_camp.shopping_api.entity;

import java.time.LocalDateTime;

import in.tech_camp.shopping_api.entity.enums.OrderStatus;
import in.tech_camp.shopping_api.entity.enums.PaymentMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  
  private UserEntity user;
  private OrderStatus status = OrderStatus.ORDERED;
  private PaymentMethod paymentMethod;
  private Integer totalPrice;
  private LocalDateTime shippedAt;
  private LocalDateTime deliveredAt;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
}
