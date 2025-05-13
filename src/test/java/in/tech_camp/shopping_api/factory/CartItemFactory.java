package in.tech_camp.shopping_api.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;

public class CartItemFactory {
  
  public static List<CartItemEntity> createCartItemsEqualsUser(UserEntity user, List<ProductEntity> products) {
    List<CartItemEntity> cartItemEntities = new ArrayList<>();
    
    int idCount = 1;
    for (ProductEntity product : products) {
      CartItemEntity cartItemEntity = new CartItemEntity();
      cartItemEntity.setId(idCount);
      cartItemEntity.setUser(user);
      cartItemEntity.setProduct(product);
      cartItemEntity.setQuantity(2);
      cartItemEntity.setCreatedAt(LocalDateTime.now());
      cartItemEntity.setUpdatedAt(LocalDateTime.now());
      cartItemEntity.setDeletedAt(null);

      cartItemEntities.add(cartItemEntity);
    }

    return cartItemEntities;
  }
}
