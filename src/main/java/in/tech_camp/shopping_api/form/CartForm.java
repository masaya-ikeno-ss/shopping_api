package in.tech_camp.shopping_api.form;

import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartForm {

  @NotNull(message = "ユーザーIDは必須です", groups = ValidationPriority1.class)
  private Integer userId;

  @NotNull(message = "商品IDは必須です", groups = ValidationPriority1.class)
  private Integer productId;

  @NotNull(message = "数量は必須です", groups = ValidationPriority1.class)
  @Min(value = 1, message = "数量は1以上で指定してください", groups = ValidationPriority2.class)
  private Integer quantity;
}
