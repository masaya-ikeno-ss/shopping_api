package in.tech_camp.shopping_api.controller.users;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.tech_camp.shopping_api.config.SecurityConfig;
import in.tech_camp.shopping_api.controller.UserController;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.form.UserForm;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.UserService;

@WebMvcTest(UserController.class)
@Import(SecurityConfig.class)
public class PutUserTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserQueryService userQueryService;

  @MockBean
  UserService userService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void ユーザー情報更新が成功した場合() throws Exception {
    UserForm userForm = UserFactory.createDefaultUserForm();
    Integer id = 1;
    UserEntity userEntity = new UserEntity();

    Mockito.when(userQueryService.getUserById(id)).thenReturn(userEntity);
    Mockito.doNothing().when(userService).updateUser(userForm, id);

    mockMvc.perform(put("/users/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userForm)))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void ユーザー情報更新が失敗した場合() throws Exception {
    UserForm userForm = UserFactory.createDefaultUserForm();
    Integer id = 1;
    UserEntity userEntity = new UserEntity();

    Mockito.when(userQueryService.getUserById(id)).thenReturn(userEntity);

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(userService).updateUser(userForm, id);

    mockMvc.perform(put("/users/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userForm)))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }

  @Test
  void 情報を更新したいユーザーが存在しない場合() throws Exception {
    UserForm userForm = UserFactory.createDefaultUserForm();
    Integer id = 99999;
    UserEntity userEntity = new UserEntity();

    Mockito.when(userQueryService.getUserById(id)).thenReturn(userEntity);

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(userService).updateUser(userForm, id);

    mockMvc.perform(put("/users/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userForm)))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }
}

