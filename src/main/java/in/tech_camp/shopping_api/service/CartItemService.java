package in.tech_camp.shopping_api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.repository.CartItemRepository;

@Service
public class CartItemService {
  private final CartItemRepository cartItemRepository;

  public CartItemService(CartItemRepository cartItemRepository) {
    this.cartItemRepository = cartItemRepository;
  }

  public void deleteCart(CartItemEntity cartItemEntity) {
    cartItemEntity.setDeletedAt(LocalDateTime.now());
    cartItemRepository.save(cartItemEntity);
  }
}
