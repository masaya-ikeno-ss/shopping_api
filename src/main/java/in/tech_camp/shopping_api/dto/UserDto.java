package in.tech_camp.shopping_api.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
  private Integer id;
  private String UserName;
  private String email;
  private String phoneNumber;
  private String postcode;
  private String address;
  private String iconImage;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
