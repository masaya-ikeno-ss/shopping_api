package in.tech_camp.shopping_api.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderPreviewResponseDto {
  private Integer userId;
  private String paymentMethod;
  private Integer totalPrice;
  private List<OrderPreviewItemDto> items;
}
