package in.tech_camp.shopping_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.form.CartForm;
import in.tech_camp.shopping_api.repository.CartItemRepository;
import in.tech_camp.shopping_api.repository.ProductRepository;
import in.tech_camp.shopping_api.repository.UserRepository;

@Service
public class CartItemService {
  private final CartItemRepository cartItemRepository;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public CartItemService(
    CartItemRepository cartItemRepository,
    UserRepository userRepository,
    ProductRepository productRepository) {
    this.cartItemRepository = cartItemRepository;
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  public void addToCart(CartForm cartForm) {
    CartItemEntity cartItemEntity = new CartItemEntity();
    UserEntity userEntity = userRepository.findByIdAndDeletedAtIsNull(cartForm.getUserId());
    ProductEntity productEntity = productRepository.findByIdAndDeletedAtIsNull(cartForm.getProductId());
    cartItemEntity.setUser(userEntity);
    cartItemEntity.setProduct(productEntity);
    cartItemEntity.setQuantity(cartForm.getQuantity());
    cartItemEntity.setCreatedAt(LocalDateTime.now());
    cartItemEntity.setUpdatedAt(LocalDateTime.now());
    cartItemEntity.setDeletedAt(null);

    cartItemRepository.save(cartItemEntity);
  }

  public void updateCart(CartForm cartForm, Integer id) {
    CartItemEntity cartItemEntity = cartItemRepository.findByIdAndDeletedAtIsNull(id);
    UserEntity userEntity = userRepository.findByIdAndDeletedAtIsNull(cartForm.getUserId());
    ProductEntity productEntity = productRepository.findByIdAndDeletedAtIsNull(cartForm.getProductId());
    cartItemEntity.setUser(userEntity);
    cartItemEntity.setProduct(productEntity);
    cartItemEntity.setQuantity(cartForm.getQuantity());
    cartItemEntity.setUpdatedAt(LocalDateTime.now());

    cartItemRepository.save(cartItemEntity);
  }

  public void deleteCart(CartItemEntity cartItemEntity) {
    cartItemEntity.setDeletedAt(LocalDateTime.now());
    cartItemRepository.save(cartItemEntity);
  }

  public void deleteCartsByUser(UserEntity userEntity) {
    List<CartItemEntity> cartItemEntities = cartItemRepository.findByUserIdAndDeletedAtIsNull(userEntity.getId());
    for (CartItemEntity cartItemEntity : cartItemEntities) {
      cartItemEntity.setDeletedAt(LocalDateTime.now());
    }
    cartItemRepository.saveAll(cartItemEntities);
  }
}
