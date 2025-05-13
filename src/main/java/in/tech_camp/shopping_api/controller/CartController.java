package in.tech_camp.shopping_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;
import in.tech_camp.shopping_api.service.CartItemService;



@RestController
@RequestMapping("/carts")
public class CartController {

  private final CartItemService cartItemService;
  private final CartItemQueryService cartItemQueryService;

  public CartController(CartItemQueryService cartItemQueryService, CartItemService cartItemService) {
    this.cartItemQueryService = cartItemQueryService;
    this.cartItemService = cartItemService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CartItemEntity> findCartById(@PathVariable Integer id) {
    try {
      CartItemEntity cartItemEntity = cartItemQueryService.findById(id);
      if (cartItemEntity == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(cartItemEntity);
    } catch (Exception e) {
     return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<List<CartItemEntity>> findCartsByUserId(@PathVariable Integer userId) {
    try {
      List<CartItemEntity> cartItemEntities = cartItemQueryService.findByUserId(userId);
      if (cartItemEntities == null || cartItemEntities.isEmpty()) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(cartItemEntities);
    } catch (Exception e) {
     return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCart(@PathVariable Integer id) {
    try {
      CartItemEntity cartItemEntity = cartItemQueryService.findById(id);
      if (cartItemEntity == null) {
        return ResponseEntity.notFound().build();
      }
      cartItemService.deleteCart(cartItemEntity);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
