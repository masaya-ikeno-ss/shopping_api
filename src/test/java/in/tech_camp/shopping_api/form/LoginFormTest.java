package in.tech_camp.shopping_api.form;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import in.tech_camp.shopping_api.validation.ValidationPriority3;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class LoginFormTest {
  private Validator validator;
  private LoginForm loginForm;

  @BeforeEach
  public void setUp() {
    loginForm = UserFactory.createDefaultLoginForm();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void メールアドレスを入力しなかった場合() {
    loginForm.setEmail("");
    Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("メールアドレスは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void メールアドレスのフォーマットに則っていない場合() {
    loginForm.setEmail("test.com");
    Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("メールアドレスの形式で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void メールアドレスが256文字以上だった場合() {
    String email = "a".repeat(256);
    loginForm.setEmail(email);
    Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm, ValidationPriority3.class);
    assertEquals(1, violations.size());
    assertEquals("メールアドレスは255文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void パスワードを入力しなかった場合() {
    loginForm.setPassword("");
    Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("パスワードは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void パスワードが256文字以上だった場合() {
    String password = "a".repeat(256);
    loginForm.setPassword(password);
    Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("パスワードは255文字以内で入力してください", violations.iterator().next().getMessage());
  }
}
