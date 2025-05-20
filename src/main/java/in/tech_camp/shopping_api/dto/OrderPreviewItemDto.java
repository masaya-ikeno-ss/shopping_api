package in.tech_camp.shopping_api.dto;

import lombok.Data;

@Data
public class OrderPreviewItemDto {
  private Integer productId;
  private String productName;
  private Integer price;
  private Integer quantity;
  private Integer subTotal;
}
