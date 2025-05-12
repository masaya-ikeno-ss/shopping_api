package in.tech_camp.shopping_api.controller.users;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.tech_camp.shopping_api.controller.UserController;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.form.LoginForm;
import in.tech_camp.shopping_api.form.UserForm;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.UserService;

@WebMvcTest(UserController.class)
public class LoginUserTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserQueryService userQueryService;

  @MockBean
  UserService userService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void ログインが成功した場合() throws Exception {
    LoginForm loginForm = UserFactory.createDefaultLoginForm();
    UserEntity loginUser = UserFactory.createUserEntity();

    Mockito.when(userService.login(loginForm)).thenReturn(loginUser);

    mockMvc.perform(post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginForm)))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void ログインが失敗した場合() throws Exception {
    LoginForm loginForm = UserFactory.createDefaultLoginForm();

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(userService).login(loginForm);

    mockMvc.perform(post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginForm)))
            .andDo(print())
            .andExpect(status().isUnauthorized());
  }
}
