package in.tech_camp.shopping_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.OrderEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.entity.enums.PaymentMethod;

@Service
public class OrderService {

  public OrderEntity returnOrder(
    PaymentMethod paymentMethod, 
    List<CartItemEntity> cartItemEntities, 
    UserEntity user) {
      OrderEntity orderEntity = new OrderEntity();
      Integer totalPrice = 0;
      for (CartItemEntity cartItemEntity : cartItemEntities) {
        ProductEntity product = cartItemEntity.getProduct();
        totalPrice += cartItemEntity.getQuantity() * product.getPrice();
      }
      orderEntity.setUser(user);
      orderEntity.setTotalPrice(totalPrice);
      orderEntity.setPaymentMethod(paymentMethod);

      return orderEntity;
    }
}
