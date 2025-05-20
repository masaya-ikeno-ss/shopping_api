package in.tech_camp.shopping_api.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CartItemDto {
  private Integer id;
  private Integer userId;
  private ProductDto product;
  private Integer quantity;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
