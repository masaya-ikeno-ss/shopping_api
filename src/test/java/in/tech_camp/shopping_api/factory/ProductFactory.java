package in.tech_camp.shopping_api.factory;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import in.tech_camp.shopping_api.entity.CategoryEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.ProductImageEntity;
import in.tech_camp.shopping_api.form.ProductForm;

public class ProductFactory {
  public static List<ProductEntity> createProductEntities() {
    // １つ目の商品
    ProductEntity product1 = new ProductEntity();
    product1.setId(1);
    product1.setProductName("千葉県産にんじん");
    product1.setPrice(200);
    product1.setStockQuantity(50);
    product1.setDescription("新鮮なニンジンです");
    product1.setCreatedAt(LocalDateTime.now());
    
    CategoryEntity category1 = new CategoryEntity();
    category1.setId(1);
    category1.setCategoryName("にんじん");
    product1.setCategories(new HashSet<>(Arrays.asList(category1)));

    ProductImageEntity image1 = new ProductImageEntity();
    image1.setId(1);
    image1.setImageUrl("images/carrot.png");
    image1.setProduct(product1);
    product1.setProductImages(Collections.singletonList(image1));


    // ２つ目の商品
    ProductEntity product2 = new ProductEntity();
    product2.setId(2);
    product2.setProductName("山梨県産ブドウ");
    product2.setPrice(5000);
    product2.setStockQuantity(10);
    product2.setDescription("新鮮なブドウです");
    product2.setCreatedAt(LocalDateTime.now());
    
    CategoryEntity category2 = new CategoryEntity();
    category2.setId(2);
    category2.setCategoryName("ぶどう");
    product2.setCategories(new HashSet<>(Arrays.asList(category2)));

    ProductImageEntity image2 = new ProductImageEntity();
    image2.setId(2);
    image2.setImageUrl("images/grape1.png");
    image2.setProduct(product2);

    ProductImageEntity image3 = new ProductImageEntity();
    image3.setId(3);
    image3.setImageUrl("images/grape2.png");
    image3.setProduct(product2);
    product2.setProductImages(Arrays.asList(image2, image3));

    return Arrays.asList(product1, product2);
  }

  public static ProductEntity createProductEntity() {
    ProductEntity product1 = new ProductEntity();
    product1.setId(1);
    product1.setProductName("千葉県産にんじん");
    product1.setPrice(200);
    product1.setStockQuantity(50);
    product1.setDescription("新鮮なニンジンです");
    product1.setCreatedAt(LocalDateTime.now());
    
    CategoryEntity category1 = new CategoryEntity();
    category1.setId(1);
    category1.setCategoryName("にんじん");
    product1.setCategories(new HashSet<>(Arrays.asList(category1)));

    ProductImageEntity image1 = new ProductImageEntity();
    image1.setId(1);
    image1.setImageUrl("images/carrot.png");
    image1.setProduct(product1);
    product1.setProductImages(Collections.singletonList(image1));

    return product1;
  }

  public static ProductForm createProductForm() {
    ProductForm productForm = new ProductForm();
    productForm.setProductName("千葉県産にんじん");
    productForm.setPrice(200);
    productForm.setStockQuantity(50);
    productForm.setDescription("新鮮なニンジンです");

    CategoryEntity category1 = new CategoryEntity();
    category1.setId(1);
    category1.setCategoryName("にんじん");

    ProductImageEntity image1 = new ProductImageEntity();
    image1.setId(1);
    image1.setImageUrl("images/carrot.png");

    productForm.setCategoryIds(Arrays.asList(category1.getId()));
    productForm.setImageIds(Arrays.asList(image1.getId()));

    return productForm;
  }
}
