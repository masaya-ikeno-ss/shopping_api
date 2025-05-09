package in.tech_camp.shopping_api.form;

import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import in.tech_camp.shopping_api.validation.ValidationPriority3;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginForm {

  @NotBlank(message = "メールアドレスは必須です", groups = ValidationPriority1.class)
  @Email(message = "メールアドレスの形式で入力してください", groups = ValidationPriority2.class)
  @Size(max = 255, message = "メールアドレスは255文字以内で入力してください", groups = ValidationPriority3.class)
  private String email;

  @NotBlank(message = "パスワードは必須です", groups = ValidationPriority1.class)
  @Size(max = 255, message = "パスワードは255文字以内で入力してください", groups = ValidationPriority2.class)
  private String password;
}
