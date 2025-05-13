package in.tech_camp.shopping_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;


@RestController
@RequestMapping("/carts")
public class CartController {
  private final CartItemQueryService cartItemQueryService;

  public CartController(CartItemQueryService cartItemQueryService) {
    this.cartItemQueryService = cartItemQueryService;
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
  
}
