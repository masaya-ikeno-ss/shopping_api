package in.tech_camp.shopping_api.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.factory.CartItemFactory;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class CartFormTest {
  
  private Validator validator;
  private CartForm cartForm;

  @BeforeEach
  public void setUp() {
    UserEntity userEntity = UserFactory.createUserEntity();
    ProductEntity productEntity = ProductFactory.createProductEntity();
    cartForm = CartItemFactory.createCartForm(userEntity, productEntity);
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void ユーザーIDを入力しなかった場合() {
    cartForm.setUserId(null);
    Set<ConstraintViolation<CartForm>> violations = validator.validate(cartForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("ユーザーIDは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 商品IDを入力しなかった場合() {
    cartForm.setProductId(null);
    Set<ConstraintViolation<CartForm>> violations = validator.validate(cartForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("商品IDは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 数量を入力しなかった場合() {
    cartForm.setQuantity(null);
    Set<ConstraintViolation<CartForm>> violations = validator.validate(cartForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("数量は必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 不適な数量を入力した場合() {
    cartForm.setQuantity(0);
    Set<ConstraintViolation<CartForm>> violations = validator.validate(cartForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("数量は1以上で指定してください", violations.iterator().next().getMessage());
  }
}
