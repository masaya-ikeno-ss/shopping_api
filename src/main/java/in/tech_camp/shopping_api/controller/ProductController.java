package in.tech_camp.shopping_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.queryService.ProductQueryService;



@RestController
@RequestMapping("/products")
public class ProductController {
  // private final ProductService productService;
  private final ProductQueryService productQueryService;

  // public ProductController(ProductQueryService productQueryService, ProductService productService) {
    public ProductController(ProductQueryService productQueryService) {
    this.productQueryService = productQueryService;
    // this.productService = productService;
  }
  
  @GetMapping("/")
  public ResponseEntity<List<ProductEntity>> getProductAll() {
      try {
        List<ProductEntity> productEntities = productQueryService.findAll();
        if (productEntities == null || productEntities.isEmpty()) {
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productEntities);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<ProductEntity> getProductById(@PathVariable Integer id) {
      try {
        ProductEntity productEntity = productQueryService.findById(id);
        if (productEntity == null) {
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productEntity);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
  }
  
}
