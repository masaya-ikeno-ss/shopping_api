package in.tech_camp.shopping_api.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.form.UserForm;
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
    try {
      UserEntity userEntity = userQueryService.getUserById(id);
      if (userEntity == null) {
        return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(userEntity);
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PostMapping("/register")
  public ResponseEntity<Void> registerUser(@RequestBody UserForm userForm) {
    try {
      userService.registerUser(userForm);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Void> updateUser(@RequestBody UserForm userForm, @PathVariable Integer id) {
    try {
      UserEntity userEntity = userQueryService.getUserById(id);
      if (userEntity == null) {
        return ResponseEntity.notFound().build();
      }
      userService.updateUser(userForm, id);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUserById(@PathVariable Integer id) {
    try {
      UserEntity userEntity = userQueryService.getUserById(id);
      if (userEntity == null) {
        return ResponseEntity.notFound().build();
      }
      userService.deleteUser(userEntity);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }
}
