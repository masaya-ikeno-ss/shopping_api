package in.tech_camp.shopping_api.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.form.LoginForm;
import in.tech_camp.shopping_api.form.UserForm;
import in.tech_camp.shopping_api.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public void deleteUser(UserEntity userEntity) {
    userEntity.setDeletedAt(LocalDateTime.now());
    userRepository.save(userEntity);
  }

  @Transactional
  public void registerUser(UserForm userForm) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUserName(userForm.getUserName());
    userEntity.setEmail(userForm.getEmail());
    String encodedPassword = encodePassword(userForm.getPassword());
    userEntity.setPassword(encodedPassword);
    userEntity.setPhoneNumber(userForm.getPhoneNumber());
    userEntity.setPostcode(userForm.getPostcode());
    userEntity.setAddress(userForm.getAddress());
    userEntity.setIconImage(userForm.getIconImage());
    userEntity.setCreatedAt(LocalDateTime.now());
    userEntity.setUpdatedAt(LocalDateTime.now());
    userEntity.setDeletedAt(null);

    userRepository.save(userEntity);
  }

  @Transactional
  public void updateUser(UserForm userForm, Integer id) {
    UserEntity userEntity = new UserEntity();
    userEntity.setId(id);
    userEntity.setUserName(userForm.getUserName());
    userEntity.setEmail(userForm.getEmail());
    String encodedPassword = encodePassword(userForm.getPassword());
    userEntity.setPassword(encodedPassword);
    userEntity.setPhoneNumber(userForm.getPhoneNumber());
    userEntity.setPostcode(userForm.getPostcode());
    userEntity.setAddress(userForm.getAddress());
    userEntity.setIconImage(userForm.getIconImage());
    userEntity.setUpdatedAt(LocalDateTime.now());
    userEntity.setDeletedAt(null);

    userRepository.save(userEntity);
  }

  public UserEntity login(LoginForm loginForm) {
    UserEntity userEntity = userRepository.findByEmailAndDeletedAtIsNull(loginForm.getEmail());

    if (!userEntity.getPassword().equals(loginForm.getPassword())) {
      throw new IllegalArgumentException("パスワードが正しくありません");
    }
    return userEntity;
  }

  private String encodePassword(String password) {
    return passwordEncoder.encode(password);
  }
}
