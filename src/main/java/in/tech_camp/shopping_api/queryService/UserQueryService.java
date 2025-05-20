package in.tech_camp.shopping_api.queryService;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.dto.UserDto;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.repository.UserRepository;

@Service
public class UserQueryService {
  private final UserRepository userRepository;

  public UserQueryService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity getUserById(Integer id) {
    return userRepository.findByIdAndDeletedAtIsNull(id);
  }
  
  public UserDto getUserByIdForDto(Integer id) {
    UserEntity userEntity = userRepository.findByIdAndDeletedAtIsNull(id);
    UserDto dto = new UserDto();
    dto.setId(userEntity.getId());
    dto.setUserName(userEntity.getUserName());
    dto.setEmail(userEntity.getEmail());
    dto.setPhoneNumber(userEntity.getPhoneNumber());
    dto.setPostcode(userEntity.getPostcode());
    dto.setAddress(userEntity.getAddress());
    dto.setIconImage(userEntity.getIconImage());
    dto.setCreatedAt(userEntity.getCreatedAt());
    dto.setUpdatedAt(userEntity.getUpdatedAt());

    return dto;
  }

}