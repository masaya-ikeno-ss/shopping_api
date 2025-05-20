package in.tech_camp.shopping_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.dto.OrderPreviewItemDto;
import in.tech_camp.shopping_api.dto.OrderPreviewResponseDto;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.entity.enums.PaymentMethod;

@Service
public class OrderService {

  public OrderPreviewResponseDto returnOrder(
    // PaymentMethod paymentMethod, 
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
      // dto.setPaymentMethod(paymentMethod.name());
      dto.setTotalPrice(totalPrice);
      dto.setItems(items);

      return dto;
    }

    public OrderPreviewResponseDto registerOrder(
    PaymentMethod paymentMethod,
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
      dto.setPaymentMethod(paymentMethod.name());
      dto.setTotalPrice(totalPrice);
      dto.setItems(items);

      return dto;
    }
}
