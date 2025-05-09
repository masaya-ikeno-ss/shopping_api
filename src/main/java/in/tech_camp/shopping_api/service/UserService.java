package in.tech_camp.shopping_api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public boolean deleteUser(UserEntity userEntity) {
    userEntity.setDeletedAt(LocalDateTime.now());
    userRepository.save(userEntity);
    return true;
  }
}
