package in.tech_camp.shopping_api.queryService;

import org.springframework.stereotype.Service;

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
  
}
