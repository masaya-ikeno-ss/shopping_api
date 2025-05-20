package in.tech_camp.shopping_api.queryService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.dto.CartItemDto;
import in.tech_camp.shopping_api.dto.ProductDto;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.CategoryEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.ProductImageEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
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

  public CartItemDto findByIdForDto(Integer id) {
    CartItemEntity cartItemEntity = cartItemRepository.findByIdAndDeletedAtIsNull(id);
    CartItemDto dto = new CartItemDto();
    dto.setId(cartItemEntity.getId());
    
    UserEntity user = cartItemEntity.getUser();
    dto.setUserId(user.getId());
    
    ProductEntity productEntity = cartItemEntity.getProduct();
    ProductDto productDto = new ProductDto();
    productDto.setId(productEntity.getId());
    productDto.setProductName(productEntity.getProductName());
    productDto.setPrice(productEntity.getPrice());
    productDto.setStockQuantity(productEntity.getStockQuantity());
    productDto.setDescription(productEntity.getDescription());

    List<ProductImageEntity> productImageEntities = productEntity.getProductImages();
    List<String> imageUrls = new ArrayList<>();
    for (ProductImageEntity image : productImageEntities) {
      imageUrls.add(image.getImageUrl());
    }
    productDto.setProductImages(imageUrls);

    Set<CategoryEntity> categoryEntities = productEntity.getCategories();
    Set<String> categories = new HashSet<>();
    for (CategoryEntity category : categoryEntities) {
      categories.add(category.getCategoryName());
    }
    productDto.setCategories(categories);

    productDto.setCreatedAt(productEntity.getCreatedAt());
    productDto.setUpdatedAt(productEntity.getUpdatedAt());

    dto.setProduct(productDto);
    dto.setQuantity(cartItemEntity.getQuantity());
    dto.setCreatedAt(cartItemEntity.getCreatedAt());
    dto.setUpdatedAt(cartItemEntity.getUpdatedAt());

    return dto;
  }

  public List<CartItemEntity> findByUserId(Integer userId) {
    return cartItemRepository.findByUserIdAndDeletedAtIsNull(userId);
  }

  public List<CartItemDto> findByUserIdForDto(Integer userId) {
    List<CartItemEntity> cartItemEntities = cartItemRepository.findByUserIdAndDeletedAtIsNull(userId);
    List<CartItemDto> dtoList = new ArrayList<>();
    for (CartItemEntity cartItemEntity : cartItemEntities) {
      CartItemDto dto = new CartItemDto();
      dto.setId(cartItemEntity.getId());
      
      UserEntity user = cartItemEntity.getUser();
      dto.setUserId(user.getId());
      
      ProductEntity productEntity = cartItemEntity.getProduct();
      ProductDto productDto = new ProductDto();
      productDto.setId(productEntity.getId());
      productDto.setProductName(productEntity.getProductName());
      productDto.setPrice(productEntity.getPrice());
      productDto.setStockQuantity(productEntity.getStockQuantity());
      productDto.setDescription(productEntity.getDescription());

      List<ProductImageEntity> productImageEntities = productEntity.getProductImages();
      List<String> imageUrls = new ArrayList<>();
      for (ProductImageEntity image : productImageEntities) {
        imageUrls.add(image.getImageUrl());
      }
      productDto.setProductImages(imageUrls);

      Set<CategoryEntity> categoryEntities = productEntity.getCategories();
      Set<String> categories = new HashSet<>();
      for (CategoryEntity category : categoryEntities) {
        categories.add(category.getCategoryName());
      }
      productDto.setCategories(categories);

      productDto.setCreatedAt(productEntity.getCreatedAt());
      productDto.setUpdatedAt(productEntity.getUpdatedAt());

      dto.setProduct(productDto);
      dto.setQuantity(cartItemEntity.getQuantity());
      dto.setCreatedAt(cartItemEntity.getCreatedAt());
      dto.setUpdatedAt(cartItemEntity.getUpdatedAt());

      dtoList.add(dto);
    }
    return dtoList;
  }
}
