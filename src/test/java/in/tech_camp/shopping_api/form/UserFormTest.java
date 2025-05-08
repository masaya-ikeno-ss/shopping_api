package in.tech_camp.shopping_api.form;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import in.tech_camp.shopping_api.factory.UserFactory;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class UserFormTest {
  private final Validator validator;

  public UserFormTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
}
  
  @Test
  void ユーザー名を入力しなかった場合() {
    UserForm userForm = UserFactory.createDefaultUserForm();
    userForm.setUserName("");
    Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm);
    assertEquals(1, violations.size());
    assertEquals("ユーザー名は必須です", violations.iterator().next().getMessage());
  }
}
