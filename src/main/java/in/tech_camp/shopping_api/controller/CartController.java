package in.tech_camp.shopping_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.dto.CartItemDto;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.form.CartForm;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.CartItemService;





@RestController
@RequestMapping("/carts")
public class CartController {

  private final CartItemService cartItemService;
  private final CartItemQueryService cartItemQueryService;
  private final UserQueryService userQueryService;

  public CartController(
    CartItemQueryService cartItemQueryService, 
    CartItemService cartItemService,
    UserQueryService userQueryService) {
    this.cartItemQueryService = cartItemQueryService;
    this.cartItemService = cartItemService;
    this.userQueryService = userQueryService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CartItemDto> findCartById(@PathVariable Integer id) {
    try {
      CartItemDto cartItemDto = cartItemQueryService.findByIdForDto(id);
      if (cartItemDto == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(cartItemDto);
    } catch (Exception e) {
     return ResponseEntity.badRequest().build();
    }
  }

  @GetMapping("/users/{userId}")
  public ResponseEntity<List<CartItemDto>> findCartsByUserId(@PathVariable Integer userId) {
    try {
      List<CartItemDto> cartItems = cartItemQueryService.findByUserIdForDto(userId);
      return ResponseEntity.ok(cartItems);
    } catch (Exception e) {
     return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/add")
  public ResponseEntity<Void> addToCart(@RequestBody @Validated CartForm cartForm) {
    try {
      cartItemService.addToCart(cartForm);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("update/{id}")
  public ResponseEntity<Void> updateCart(@RequestBody @Validated CartForm cartForm, @PathVariable Integer id) {
    try {
      CartItemEntity cartItemEntity = cartItemQueryService.findById(id);
      if (cartItemEntity == null) {
        return ResponseEntity.notFound().build();
      }
      cartItemService.updateCart(cartForm, id);
      return ResponseEntity.ok().build();
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

  @DeleteMapping("/users/{userId}")
  public ResponseEntity<Void> deleteCartsByUser(@PathVariable Integer userId) {
    try {
      UserEntity userEntity = userQueryService.getUserById(userId);
      if (userEntity == null) {
        return ResponseEntity.notFound().build();
      }
      cartItemService.deleteCartsByUser(userEntity);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
