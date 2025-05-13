package in.tech_camp.shopping_api.form;

import java.util.List;

import in.tech_camp.shopping_api.validation.ValidationPriority1;
import in.tech_camp.shopping_api.validation.ValidationPriority2;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductForm {
  @NotBlank(message = "商品名は必須です", groups = ValidationPriority1.class)
  @Size(max = 100, message = "商品名は100文字以内で入力してください", groups = ValidationPriority2.class)
  private String productName;

  @NotNull(message = "値段は必須です", groups = ValidationPriority1.class)
  @Min(value = 0, message = "値段は0円以上で入力してください", groups = ValidationPriority2.class)
  private Integer price;

  @NotNull(message = "在庫数は必須です", groups = ValidationPriority1.class)
  @Min(value = 0, message = "在庫数は0以上で入力してください", groups = ValidationPriority2.class)
  private Integer stockQuantity;

  private String description;

  @NotNull(message = "カテゴリは必須です", groups = ValidationPriority1.class)
  @Size(min = 1, message = "カテゴリを1つ以上選択してください", groups = ValidationPriority2.class)
  private List<Integer> categoryIds;

  private List<String> imageUrls;
}
