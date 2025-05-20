package in.tech_camp.shopping_api.queryService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.dto.ProductDto;
import in.tech_camp.shopping_api.entity.CategoryEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.ProductImageEntity;
import in.tech_camp.shopping_api.repository.ProductRepository;

@Service
public class ProductQueryService {
  private final ProductRepository productRepository;

  public ProductQueryService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<ProductEntity> findAll() {
    return productRepository.findAllByDeletedAtIsNull();
  }

  public List<ProductDto> findAllForDto() {
    List<ProductEntity> productEntities = productRepository.findAllByDeletedAtIsNull();
    List<ProductDto> productDtoList = new ArrayList<>();
    for (ProductEntity productEntity : productEntities) {
      ProductDto dto = new ProductDto();
      dto.setId(productEntity.getId());
      dto.setProductName(productEntity.getProductName());
      dto.setPrice(productEntity.getPrice());
      dto.setStockQuantity(productEntity.getStockQuantity());
      dto.setDescription(productEntity.getDescription());

      List<ProductImageEntity> productImageEntities = productEntity.getProductImages();
      List<String> imageUrls = new ArrayList<>();
      for (ProductImageEntity image : productImageEntities) {
        imageUrls.add(image.getImageUrl());
      }
      dto.setProductImages(imageUrls);

      Set<CategoryEntity> categoryEntities = productEntity.getCategories();
      Set<String> categories = new HashSet<>();
      for (CategoryEntity category : categoryEntities) {
        categories.add(category.getCategoryName());
      }
      dto.setCategories(categories);

      dto.setCreatedAt(productEntity.getCreatedAt());
      dto.setUpdatedAt(productEntity.getUpdatedAt());

      productDtoList.add(dto);
    }
    return productDtoList;
  }

  public ProductEntity findById(Integer id) {
    return productRepository.findByIdAndDeletedAtIsNull(id);
  }

  public ProductDto findByIdForDto(Integer id) {
    ProductEntity productEntity = productRepository.findByIdAndDeletedAtIsNull(id);
    if (productEntity == null) {
      return null;
    }
    ProductDto dto = new ProductDto();
    dto.setId(productEntity.getId());
    dto.setProductName(productEntity.getProductName());
    dto.setPrice(productEntity.getPrice());
    dto.setStockQuantity(productEntity.getStockQuantity());
    dto.setDescription(productEntity.getDescription());

    List<ProductImageEntity> productImageEntities = productEntity.getProductImages();
    List<String> imageUrls = new ArrayList<>();
    for (ProductImageEntity image : productImageEntities) {
      imageUrls.add(image.getImageUrl());
    }
    dto.setProductImages(imageUrls);

    Set<CategoryEntity> categoryEntities = productEntity.getCategories();
    Set<String> categories = new HashSet<>();
    for (CategoryEntity category : categoryEntities) {
      categories.add(category.getCategoryName());
    }
    dto.setCategories(categories);

    dto.setCreatedAt(productEntity.getCreatedAt());
    dto.setUpdatedAt(productEntity.getUpdatedAt());
    return dto;
  }

  public List<ProductEntity> findByCategoryId(Integer id) {
    return productRepository.findByCategories_IdAndDeletedAtIsNull(id);
  }

  public List<ProductDto> findByCategoryIdForDto(Integer id) {
    List<ProductEntity> productEntities = productRepository.findByCategories_IdAndDeletedAtIsNull(id);
    List<ProductDto> productDtoList = new ArrayList<>();
    for (ProductEntity productEntity : productEntities) {
      ProductDto dto = new ProductDto();
      dto.setId(productEntity.getId());
      dto.setProductName(productEntity.getProductName());
      dto.setPrice(productEntity.getPrice());
      dto.setStockQuantity(productEntity.getStockQuantity());
      dto.setDescription(productEntity.getDescription());

      List<ProductImageEntity> productImageEntities = productEntity.getProductImages();
      List<String> imageUrls = new ArrayList<>();
      for (ProductImageEntity image : productImageEntities) {
        imageUrls.add(image.getImageUrl());
      }
      dto.setProductImages(imageUrls);

      Set<CategoryEntity> categoryEntities = productEntity.getCategories();
      Set<String> categories = new HashSet<>();
      for (CategoryEntity category : categoryEntities) {
        categories.add(category.getCategoryName());
      }
      dto.setCategories(categories);

      dto.setCreatedAt(productEntity.getCreatedAt());
      dto.setUpdatedAt(productEntity.getUpdatedAt());

      productDtoList.add(dto);
    }
    return productDtoList;
  }
}
