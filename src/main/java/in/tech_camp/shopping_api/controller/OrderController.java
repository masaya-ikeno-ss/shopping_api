package in.tech_camp.shopping_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.dto.OrderPreviewResponseDto;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.entity.enums.PaymentMethod;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.CartItemService;
import in.tech_camp.shopping_api.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;
  private final UserQueryService userQueryService;
  private final CartItemQueryService cartItemQueryService;
  private final CartItemService cartItemService;

  public OrderController(
    OrderService orderService,
    UserQueryService userQueryService,
    CartItemQueryService cartItemQueryService,
    CartItemService cartItemService) {
      this.orderService = orderService;
      this.userQueryService = userQueryService;
      this.cartItemQueryService = cartItemQueryService;
      this.cartItemService = cartItemService;
  }

  @PostMapping("/carts/{userId}/orderPreview")
  public ResponseEntity<OrderPreviewResponseDto> findOrder(
    @PathVariable Integer userId) {
    try {
      UserEntity userEntity = userQueryService.getUserById(userId);
      List<CartItemEntity> cartItemEntities = cartItemQueryService.findByUserId(userId);
      if (userEntity == null || cartItemEntities == null || cartItemEntities.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      OrderPreviewResponseDto order = orderService.returnOrder(cartItemEntities, userEntity);
      return ResponseEntity.ok(order);
    } catch (Exception e) {
     return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/carts/{userId}/order")
  public ResponseEntity<?> registerOrder(
    @RequestBody PaymentMethod paymentMethod,
    @PathVariable Integer userId) {
    try {
      UserEntity userEntity = userQueryService.getUserById(userId);
      List<CartItemEntity> cartItemEntities = cartItemQueryService.findByUserId(userId);
      if (userEntity == null || cartItemEntities == null || cartItemEntities.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      orderService.registerOrder(paymentMethod, cartItemEntities, userEntity);
      cartItemService.deleteCartsByUser(userEntity);
      return ResponseEntity.ok().build();
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(409).body(e.getMessage());
    } catch (Exception e) {
     return ResponseEntity.badRequest().build();
    }
  }
}
