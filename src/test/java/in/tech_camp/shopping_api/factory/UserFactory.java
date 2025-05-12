package in.tech_camp.shopping_api.factory;

import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.form.LoginForm;
import in.tech_camp.shopping_api.form.UserForm;

public class UserFactory {
  public static UserForm createDefaultUserForm() {
        UserForm form = new UserForm();
        form.setUserName("テストユーザー");
        form.setEmail("test@example.com");
        form.setPassword("password123");
        form.setConfirmPassword("password123");
        form.setPhoneNumber("090-1111-2222");
        form.setPostcode("1234567");
        form.setAddress("東京都テスト区1-2-3");
        form.setIconImage("icon.png");
        return form;
    }

    public static LoginForm createDefaultLoginForm() {
      LoginForm form = new LoginForm();
      form.setEmail("test@example.com");
      form.setPassword("password123");
      return form;
  }

  public static UserEntity createUserEntity() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName("テストユーザー");
    userEntity.setEmail("test@example.com");
    userEntity.setPassword("password123");
    userEntity.setPhoneNumber("090-1111-2222");
    userEntity.setPostcode("1234567");
    userEntity.setAddress("東京都テスト区1-2-3");
    userEntity.setIconImage("icon.png");
    return userEntity;
  }
}
