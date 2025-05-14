package in.tech_camp.shopping_api.controller.carts;

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

import in.tech_camp.shopping_api.controller.CartController;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.factory.CartItemFactory;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.form.CartForm;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.CartItemService;

@WebMvcTest(CartController.class)
public class PostCartItemTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CartItemQueryService cartItemQueryService;

  @MockBean
  CartItemService cartItemService;

  @MockBean
  UserQueryService userQueryService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void カートへの登録が成功した場合() throws Exception {
    UserEntity userEntity = UserFactory.createUserEntity();
    ProductEntity productEntity = ProductFactory.createProductEntity();
    CartForm cartForm = CartItemFactory.createCartForm(userEntity, productEntity);

    Mockito.doNothing().when(cartItemService).addToCart(cartForm);

    mockMvc.perform(post("/carts/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cartForm)))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void カートへの登録が失敗した場合() throws Exception {
    UserEntity userEntity = UserFactory.createUserEntity();
    ProductEntity productEntity = ProductFactory.createProductEntity();
    CartForm cartForm = CartItemFactory.createCartForm(userEntity, productEntity);
    cartForm.setProductId(null);

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(cartItemService).addToCart(cartForm);

    mockMvc.perform(post("/carts/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cartForm)))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }
}