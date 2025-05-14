package in.tech_camp.shopping_api.controller.carts;

import java.util.List;

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

import in.tech_camp.shopping_api.controller.CartController;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.factory.CartItemFactory;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.CartItemService;

@WebMvcTest(CartController.class)
public class GetCartItemsByUserIdTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CartItemQueryService cartItemQueryService;

  @MockBean
  CartItemService cartItemService;

  @MockBean
  UserQueryService userQueryService;

  @Test
  void ユーザー情報を用いてカート情報の取得ができた場合() throws Exception {
    UserEntity user = UserFactory.createUserEntity();
    List<ProductEntity> products = ProductFactory.createProductEntities();

    List<CartItemEntity> cartItems = CartItemFactory.createCartItemsEqualsUser(user, products);

    Mockito.when(cartItemQueryService.findByUserId(user.getId())).thenReturn(cartItems);

    mockMvc.perform(get("/carts/users/" + user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void ユーザー情報を用いてカート情報の取得ができなかった場合() throws Exception {
    UserEntity user = UserFactory.createUserEntity();

    Mockito.when(cartItemQueryService.findByUserId(user.getId())).thenReturn(null);

    mockMvc.perform(get("/carts/users/" + user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
  }
}
