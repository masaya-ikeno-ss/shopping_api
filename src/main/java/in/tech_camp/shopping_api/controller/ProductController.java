package in.tech_camp.shopping_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.dto.ProductDto;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.form.ProductForm;
import in.tech_camp.shopping_api.queryService.ProductQueryService;
import in.tech_camp.shopping_api.service.ProductService;





@RestController
@RequestMapping("/products")
public class ProductController {
  private final ProductService productService;
  private final ProductQueryService productQueryService;

  public ProductController(
    ProductQueryService productQueryService, 
    ProductService productService) {
    this.productQueryService = productQueryService;
    this.productService = productService;
  }
  
  @GetMapping("/")
  public ResponseEntity<List<ProductDto>> getProductAll() {
      try {
        List<ProductDto> productDtos = productQueryService.findAllForDto();
        return ResponseEntity.ok(productDtos);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
  }
  
  @GetMapping("/{productId}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable Integer productId) {
      try {
        ProductDto productDto = productQueryService.findByIdForDto(productId);
        if (productDto == null) {
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDto);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
  }

  @GetMapping("/category/{categoryId}")
  public ResponseEntity<List<ProductDto>> getProductByCategoryId(@PathVariable Integer categoryId) {
      try {
        List<ProductDto> productDtos = productQueryService.findByCategoryIdForDto(categoryId);
        return ResponseEntity.ok(productDtos);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
  }

  @PostMapping("/register")
  public ResponseEntity<Void> registerProduct(@RequestBody @Validated ProductForm product) {
    try {
      productService.registerProduct(product);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
  
  @PutMapping("update/{id}")
  public ResponseEntity<Void> updateProduct(@RequestBody @Validated ProductForm productForm, @PathVariable Integer id) {
    try {
      ProductEntity productEntity = productQueryService.findById(id);
      if (productEntity == null) {
        return ResponseEntity.notFound().build();
      }
      productService.updateProduct(productForm, id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
    try {
      ProductEntity productEntity = productQueryService.findById(id);
      if (productEntity == null) {
        return ResponseEntity.notFound().build();
      }
      productService.deleteProduct(productEntity);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
