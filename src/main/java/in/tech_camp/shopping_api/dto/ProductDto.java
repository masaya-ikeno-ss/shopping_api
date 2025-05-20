package in.tech_camp.shopping_api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
public class ProductDto {
  private Integer id;
  private String productName;
  private Integer price;
  private Integer stockQuantity;
  private String description;
  private List<String> productImages;
  private Set<String> categories;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
