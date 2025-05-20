package in.tech_camp.shopping_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.tech_camp.shopping_api.entity.CategoryEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.ProductImageEntity;
import in.tech_camp.shopping_api.form.ProductForm;
import in.tech_camp.shopping_api.repository.CategoryRepository;
import in.tech_camp.shopping_api.repository.ProductCategoryRepository;
import in.tech_camp.shopping_api.repository.ProductImageRepository;
import in.tech_camp.shopping_api.repository.ProductRepository;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductImageRepository productImageRepository;
  private final ProductCategoryRepository productCategoryRepository;
  private final CategoryRepository categoryRepository;

  public ProductService(
    ProductRepository productRepository,
    ProductImageRepository productImageRepository,
    ProductCategoryRepository productCategoryRepository,
    CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.productImageRepository = productImageRepository;
    this.productCategoryRepository = productCategoryRepository;
    this.categoryRepository = categoryRepository;
  }

  @Transactional
  public void registerProduct(ProductForm productForm) {
    ProductEntity productEntity = new ProductEntity();
    productEntity.setProductName(productForm.getProductName());
    productEntity.setPrice(productForm.getPrice());
    productEntity.setStockQuantity(productForm.getStockQuantity());
    productEntity.setDescription(productForm.getDescription());
    
    Set<CategoryEntity> categories = new HashSet<>(categoryRepository.findAllById(productForm.getCategoryIds()));
    productEntity.setCategories(categories);
    
    List<ProductImageEntity> productImageEntities = new ArrayList<>();
    for (String productImageUrl : productForm.getImageUrls()) {
      ProductImageEntity productImageEntity = new ProductImageEntity();
      productImageEntity.setImageUrl(productImageUrl);
      productImageEntity.setProduct(productEntity);
      productImageEntities.add(productImageEntity);
    }
    productEntity.setProductImages(productImageEntities);

    productRepository.save(productEntity);
  }

  @Transactional
  public void updateProduct(ProductForm productForm, Integer productId) {
    ProductEntity productEntity = new ProductEntity();
    productEntity.setId(productId);
    productEntity.setProductName(productForm.getProductName());
    productEntity.setPrice(productForm.getPrice());
    productEntity.setStockQuantity(productForm.getStockQuantity());
    productEntity.setDescription(productForm.getDescription());
    productEntity.setUpdatedAt(LocalDateTime.now());
    
    Set<CategoryEntity> categories = new HashSet<>(categoryRepository.findAllById(productForm.getCategoryIds()));
    productEntity.setCategories(categories);
    
    List<ProductImageEntity> productImageEntities = new ArrayList<>();
    for (String productImageUrl : productForm.getImageUrls()) {
      ProductImageEntity productImageEntity = new ProductImageEntity();
      productImageEntity.setImageUrl(productImageUrl);
      productImageEntity.setProduct(productEntity);
      productImageEntities.add(productImageEntity);
    }
    productEntity.setProductImages(productImageEntities);

    productRepository.save(productEntity);
  }

  @Transactional
  public void deleteProduct(ProductEntity productEntity) {
    productEntity.setDeletedAt(LocalDateTime.now());
    productRepository.save(productEntity);
    
    productImageRepository.deleteAllByProductId(productEntity.getId(), LocalDateTime.now());
    productCategoryRepository.logicalDeleteAllByProductId(productEntity.getId(), LocalDateTime.now());
  }
}
