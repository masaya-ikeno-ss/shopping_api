package in.tech_camp.shopping_api.form;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.springframework.validation.BindingResult;

import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import in.tech_camp.shopping_api.validation.ValidationPriority3;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserFormTest {
  private Validator validator;
  private BindingResult bindingResult;
  private UserForm userForm;

  @BeforeEach
  public void setUp() {
    userForm = UserFactory.createDefaultUserForm();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
    bindingResult = Mockito.mock(BindingResult.class);
}
  
  @Test
  void ユーザー名を入力しなかった場合() {
    userForm.setUserName("");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("ユーザー名は必須です", violations.iterator().next().getMessage());
  }

  @Test
  void ユーザー名が51文字以上だった場合() {
    String userName = "a".repeat(51);
    userForm.setUserName(userName);
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("ユーザー名は50文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void メールアドレスを入力しなかった場合() {
    userForm.setEmail("");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("メールアドレスは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void メールアドレスのフォーマットに則っていない場合() {
    userForm.setEmail("test.com");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("メールアドレスの形式で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void メールアドレスが256文字以上だった場合() {
    String email = "a".repeat(256);
    userForm.setEmail(email);
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority3.class);
    assertEquals(1, violations.size());
    assertEquals("メールアドレスは255文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void パスワードを入力しなかった場合() {
    userForm.setPassword("");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("パスワードは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void パスワードが256文字以上だった場合() {
    String password = "a".repeat(256);
    userForm.setPassword(password);
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("パスワードは255文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void 確認用パスワードを入力しなかった場合() {
    userForm.setConfirmPassword("");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("確認用パスワードは必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 確認用パスワードが256文字以上だった場合() {
    String password = "a".repeat(256);
    userForm.setConfirmPassword(password);
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("確認用パスワードは255文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void 電話番号が21文字以上だった場合() {
    userForm.setPhoneNumber("012345678901234567890");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("電話番号は20文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void 郵便番号を入力しなかった場合() {
    userForm.setPostcode("");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("郵便番号は必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 郵便番号が256文字以上だった場合() {
    String postcode = "01234567890";
    userForm.setPostcode(postcode);
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("郵便番号は10文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void 住所を入力しなかった場合() {
    userForm.setAddress("");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("住所は必須です", violations.iterator().next().getMessage());
  }

  @Test
  void 住所が256文字以上だった場合() {
    String address = "a".repeat(256);
    userForm.setAddress(address);
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority2.class);
    assertEquals(1, violations.size());
    assertEquals("住所は255文字以内で入力してください", violations.iterator().next().getMessage());
  }

  @Test
  void 画像パスが256文字以上だった場合() {
    String iconImage = "a".repeat(256);
    userForm.setIconImage(iconImage);
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ValidationPriority1.class);
    assertEquals(1, violations.size());
    assertEquals("アイコン画像パスは255文字以内にしてください", violations.iterator().next().getMessage());
  }

  @Test
    public void passwordとconfirmPasswordが不一致では登録できない() {
      userForm.setConfirmPassword("differentPassword");
      userForm.validatePasswordConfirmation(bindingResult);
      verify(bindingResult).rejectValue("passwordConfirmation", null, "パスワードが一致しません");
    }
}
