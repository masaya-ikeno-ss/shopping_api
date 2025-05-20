package in.tech_camp.shopping_api.queryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.dto.OrderPreviewItemDto;
import in.tech_camp.shopping_api.dto.OrderPreviewResponseDto;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.OrderEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.repository.OrderRepository;

@Service
public class OrderQueryService {
  private final OrderRepository orderRepository;

  public OrderPreviewResponseDto returnOrder(
    List<CartItemEntity> cartItemEntities, 
    UserEntity user) {
      Integer totalPrice = 0;
      List<OrderPreviewItemDto> items = new ArrayList<>();

      for (CartItemEntity cartItemEntity : cartItemEntities) {
        ProductEntity product = cartItemEntity.getProduct();
        Integer subTotal = cartItemEntity.getQuantity() * product.getPrice();

        OrderPreviewItemDto itemDto = new OrderPreviewItemDto();
        itemDto.setProductId(product.getId());
        itemDto.setProductName(product.getProductName());
        itemDto.setQuantity(cartItemEntity.getQuantity());
        itemDto.setPrice(product.getPrice());
        itemDto.setSubTotal(subTotal);

        items.add(itemDto);
        totalPrice += subTotal;
      }

      OrderPreviewResponseDto dto = new OrderPreviewResponseDto();
      dto.setUserId(user.getId());
      dto.setTotalPrice(totalPrice);
      dto.setItems(items);

      return dto;
    }

  public OrderQueryService(OrderRepository orderRepository) {
      this.orderRepository = orderRepository;
  }

  public OrderEntity findById(Integer orderId) {
    return orderRepository.findByIdAndDeletedAtIsNull(orderId);
  }
}
