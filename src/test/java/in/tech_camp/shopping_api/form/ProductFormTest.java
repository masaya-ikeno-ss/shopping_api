package in.tech_camp.shopping_api.form;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BindingResult;

import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ProductFormTest {
  private Validator validator;
  private BindingResult bindingResult;
  private ProductForm productForm;

  @BeforeEach
  public void setUp() {
    productForm = ProductFactory.createProductForm();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    bindingResult = Mockito.mock(BindingResult.class);
  }

  @Test
  void 商品名を入力しなかった場合() {
    productForm.setProductName("");
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("商品名は必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 商品名が100文字以上だった場合() {
    String productName = "a".repeat(101);
    productForm.setProductName(productName);
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("商品名は100文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void 値段を入力しなかった場合() {
    productForm.setPrice(null);
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("値段は必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 値段が0円だった場合() {
    productForm.setPrice(-1);
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("値段は0円以上で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void 在庫数を入力しなかった場合() {
    productForm.setStockQuantity(null);
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("在庫数は必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 在庫数が0だった場合() {
    productForm.setStockQuantity(-1);
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("在庫数は0以上で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void カテゴリを入力しなかった場合() {
    List<Integer> categoryIds = null;
    productForm.setCategoryIds(categoryIds);
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("カテゴリは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void カテゴリを選択しなかった場合() {
    List<Integer> categoryIds = Collections.emptyList();
    productForm.setCategoryIds(categoryIds);
    Set<ConstraintViolation<ProductForm>> violations = validator.validate(productForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("カテゴリを1つ以上選択してください", violations.iterator().next().getMessage());
  }
}
