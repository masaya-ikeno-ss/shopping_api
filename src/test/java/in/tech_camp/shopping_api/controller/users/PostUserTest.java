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
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.form.UserForm;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.UserService;

@WebMvcTest(UserController.class)
public class PostUserTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserQueryService userQueryService;

  @MockBean
  UserService userService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void ユーザー登録が成功した場合() throws Exception {
    UserForm userForm = UserFactory.createDefaultUserForm();

    Mockito.doNothing().when(userService).registerUser(userForm);

    mockMvc.perform(post("/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userForm)))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void ユーザー登録が失敗した場合() throws Exception {
    UserForm userForm = UserFactory.createDefaultUserForm();
    userForm.setUserName("");

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(userService).registerUser(userForm);

    mockMvc.perform(post("/users/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userForm)))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }
}

