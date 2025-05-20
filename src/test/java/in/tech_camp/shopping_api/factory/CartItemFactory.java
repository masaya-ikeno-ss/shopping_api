package in.tech_camp.shopping_api.factory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import in.tech_camp.shopping_api.dto.CartItemDto;
import in.tech_camp.shopping_api.dto.ProductDto;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.form.CartForm;

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

  public static CartItemEntity createCartItem(UserEntity user, ProductEntity product) {
    CartItemEntity cartItemEntity = new CartItemEntity();
    cartItemEntity.setId(1);
    cartItemEntity.setUser(user);
    cartItemEntity.setProduct(product);
    cartItemEntity.setQuantity(2);
    cartItemEntity.setCreatedAt(LocalDateTime.now());
    cartItemEntity.setUpdatedAt(LocalDateTime.now());
    cartItemEntity.setDeletedAt(null);

    return cartItemEntity;
  }

  public static CartForm createCartForm(UserEntity userEntity, ProductEntity productEntity) {
    CartForm cartForm = new CartForm();
    cartForm.setUserId(userEntity.getId());
    cartForm.setProductId(productEntity.getId());
    cartForm.setQuantity(2);

    return cartForm;
  }

  public static CartItemDto createCartItemDto(UserEntity user, ProductDto product) {
    CartItemDto dto = new CartItemDto();
    dto.setId(1);
    dto.setUserId(user.getId());
    dto.setProduct(product);
    dto.setQuantity(2);
    dto.setCreatedAt(LocalDateTime.now());
    return dto;
  }

  public static List<CartItemDto> createItemDtoList(UserEntity user, List<ProductDto> products) {
    List<CartItemDto> dtoList = new ArrayList<>();
    for (ProductDto product : products) {
      int count = 1;
      CartItemDto dto = new CartItemDto();
      dto.setId(count);
      dto.setUserId(user.getId());
      dto.setProduct(product);
      dto.setQuantity(count);
      dto.setCreatedAt(LocalDateTime.now());

      count += 1;
      dtoList.add(dto);
    }
    return dtoList;
  }
}
