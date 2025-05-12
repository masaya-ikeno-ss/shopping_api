package in.tech_camp.shopping_api.queryService;

import java.util.List;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.ProductEntity;
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
}
