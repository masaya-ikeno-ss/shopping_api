package in.tech_camp.shopping_api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.repository.ProductCategoryRepository;
import in.tech_camp.shopping_api.repository.ProductImageRepository;
import in.tech_camp.shopping_api.repository.ProductRepository;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductImageRepository productImageRepository;
  private final ProductCategoryRepository productCategoryRepository;

  public ProductService(
    ProductRepository productRepository,
    ProductImageRepository productImageRepository,
    ProductCategoryRepository productCategoryRepository) {
    this.productRepository = productRepository;
    this.productImageRepository = productImageRepository;
    this.productCategoryRepository = productCategoryRepository;
  }

  @Transactional
  public void deleteProduct(ProductEntity productEntity) {
    productEntity.setDeletedAt(LocalDateTime.now());
    productRepository.save(productEntity);
    
    productImageRepository.deleteAllByProductId(productEntity.getId(), LocalDateTime.now());
    productCategoryRepository.logicalDeleteAllByProductId(productEntity.getId(), LocalDateTime.now());
  }
}
