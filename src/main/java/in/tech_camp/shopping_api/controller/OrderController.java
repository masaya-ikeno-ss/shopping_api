package in.tech_camp.shopping_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.OrderEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.entity.enums.PaymentMethod;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;
  private final UserQueryService userQueryService;
  private final CartItemQueryService cartItemQueryService;

  public OrderController(
    OrderService orderService,
    UserQueryService userQueryService,
    CartItemQueryService cartItemQueryService) {
      this.orderService = orderService;
      this.userQueryService = userQueryService;
      this.cartItemQueryService = cartItemQueryService;
  }

  @PostMapping("/carts/{userId}/orderPreview")
  public ResponseEntity<OrderEntity> findOrder(
    @RequestBody PaymentMethod paymentMethod, 
    @PathVariable Integer userId) {
    try {
      UserEntity userEntity = userQueryService.getUserById(userId);
      List<CartItemEntity> cartItemEntities = cartItemQueryService.findByUserId(userId);
      if (userEntity == null || cartItemEntities == null || cartItemEntities.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      OrderEntity order = orderService.returnOrder(paymentMethod, cartItemEntities, userEntity);      
      return ResponseEntity.ok(order);
    } catch (Exception e) {
     return ResponseEntity.badRequest().build();
    }
  }
}
