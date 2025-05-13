package in.tech_camp.shopping_api.queryService;

import java.util.List;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.repository.CartItemRepository;

@Service
public class CartItemQueryService {
  private final CartItemRepository cartItemRepository;

  public CartItemQueryService(CartItemRepository cartItemRepository) {
    this.cartItemRepository = cartItemRepository;
  }

  public CartItemEntity findById(Integer id) {
    return cartItemRepository.findByIdAndDeletedAtIsNull(id);
  }

  public List<CartItemEntity> findByUserId(Integer userId) {
    return cartItemRepository.findByUserIdAndDeletedAtIsNull(userId);
  }
}
