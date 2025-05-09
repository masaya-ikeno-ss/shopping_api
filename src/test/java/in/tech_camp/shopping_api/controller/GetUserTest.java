package in.tech_camp.shopping_api.controller;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.form.UserForm;
import in.tech_camp.shopping_api.queryService.UserQueryService;

@WebMvcTest(UserController.class)
public class GetUserTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserQueryService userQueryService;

  @Test
  void ユーザー情報を取得できた場合() throws Exception {
    UserForm userForm = UserFactory.createDefaultUserForm();
    UserEntity user = new UserEntity();
    user.setId(1);
    user.setUserName(userForm.getUserName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setPostcode(userForm.getPostcode());
    user.setAddress(userForm.getAddress());
    user.setIconImage(userForm.getIconImage());
    user.setCreatedAt(LocalDateTime.now());
    user.setUpdatedAt(LocalDateTime.now());
    user.setDeletedAt(null);

    Mockito.when(userQueryService.getUserById(user.getId())).thenReturn(user);

    mockMvc.perform(get("/users/" + user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void ユーザー情報を取得できなかった場合() throws Exception {
    Mockito.when(userQueryService.getUserById(1)).thenReturn(null);

    mockMvc.perform(get("/users/" + 1)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
  }
}
