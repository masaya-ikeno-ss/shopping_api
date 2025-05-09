package in.tech_camp.shopping_api.form;

import org.springframework.validation.BindingResult;

import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import in.tech_camp.shopping_api.validation.ValidationPriority3;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
  @NotBlank(message = "ユーザー名は必須です", groups = ValidationPriority1.class)
  @Size(max = 50, message = "ユーザー名は50文字以内で入力してください", groups = ValidationPriority2.class)
  private String userName;

  @NotBlank(message = "メールアドレスは必須です", groups = ValidationPriority1.class)
  @Email(message = "メールアドレスの形式で入力してください", groups = ValidationPriority2.class)
  @Size(max = 255, message = "メールアドレスは255文字以内で入力してください", groups = ValidationPriority3.class)
  private String email;

  @NotBlank(message = "パスワードは必須です", groups = ValidationPriority1.class)
  @Size(max = 255, message = "パスワードは255文字以内で入力してください", groups = ValidationPriority2.class)
  private String password;

  @NotBlank(message = "確認用パスワードは必須です", groups = ValidationPriority1.class)
  @Size(max = 255, message = "確認用パスワードは255文字以内で入力してください", groups = ValidationPriority2.class)
  private String confirmPassword;

  @Size(max = 20, message = "電話番号は20文字以内で入力してください", groups = ValidationPriority1.class)
  private String phoneNumber;

  @NotBlank(message = "郵便番号は必須です", groups = ValidationPriority1.class)
  @Size(max = 10, message = "郵便番号は10文字以内で入力してください", groups = ValidationPriority2.class)
  private String postcode;

  @NotBlank(message = "住所は必須です", groups = ValidationPriority1.class)
  @Size(max = 255, message = "住所は255文字以内で入力してください", groups = ValidationPriority2.class)
  private String address;

  @Size(max = 255, message = "アイコン画像パスは255文字以内にしてください", groups = ValidationPriority1.class)
  private String iconImage;

  public void validatePasswordConfirmation(BindingResult result) {
      if (!password.equals(confirmPassword)) {
          result.rejectValue("passwordConfirmation", null, "パスワードが一致しません");
      }
  }
}
