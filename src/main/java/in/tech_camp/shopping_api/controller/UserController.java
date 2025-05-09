package in.tech_camp.shopping_api.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
  private final UserQueryService userQueryService;

  public UserController(UserQueryService userQueryService, UserService userService) {
    this.userQueryService = userQueryService;
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
      UserEntity userEntity = userQueryService.getUserById(id);
      if (userEntity == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(userEntity);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
    UserEntity userEntity = userQueryService.getUserById(id);
    if (userEntity == null) {
      return ResponseEntity.badRequest().build();
    }
    userService.deleteUser(userEntity);
    return ResponseEntity.noContent().build();
  }
}
