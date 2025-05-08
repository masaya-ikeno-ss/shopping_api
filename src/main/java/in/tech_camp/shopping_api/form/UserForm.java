package in.tech_camp.shopping_api.form;

import org.springframework.validation.BindingResult;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {
  @NotBlank(message = "ユーザー名は必須です")
  @Size(max = 50, message = "ユーザー名は50文字以内で入力してください")
  private String userName;

  @NotBlank(message = "メールアドレスは必須です")
  @Email(message = "メールアドレスの形式で入力してください")
  @Size(max = 255, message = "メールアドレスは255文字以内で入力してください")
  private String email;

  @NotBlank(message = "パスワードは必須です")
  @Size(max = 255, message = "パスワードは255文字以内で入力してください")
  private String password;

  @NotBlank(message = "パスワード（確認）は必須です")
  @Size(max = 255, message = "パスワード（確認）は255文字以内で入力してください")
  private String confirmPassword;

  @Size(max = 20, message = "電話番号は20文字以内で入力してください")
  private String phoneNumber;

  @NotBlank(message = "郵便番号は必須です")
  @Size(max = 10, message = "郵便番号は10文字以内で入力してください")
  private String postcode;

  @NotBlank(message = "住所は必須です")
  @Size(max = 255, message = "住所は255文字以内で入力してください")
  private String address;

  @Size(max = 255, message = "アイコン画像パスは255文字以内にしてください")
  private String iconImage;

  public void validatePasswordConfirmation(BindingResult result) {
      if (!password.equals(confirmPassword)) {
          result.rejectValue("passwordConfirmation", null, "パスワードが一致しません");
      }
  }
}
