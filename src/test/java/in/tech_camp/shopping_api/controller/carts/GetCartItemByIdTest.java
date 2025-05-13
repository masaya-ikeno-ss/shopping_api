package in.tech_camp.shopping_api.controller.carts;

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
import in.tech_camp.shopping_api.service.CartItemService;

@WebMvcTest(CartController.class)
public class GetCartItemByIdTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CartItemQueryService cartItemQueryService;

  @MockBean
  CartItemService cartItemService;

  @Test
  void IDを用いてカート情報の取得ができた場合() throws Exception {
    UserEntity user = UserFactory.createUserEntity();
    ProductEntity product = ProductFactory.createProductEntity();

    CartItemEntity cartItem = CartItemFactory.createCartItem(user, product);

    Mockito.when(cartItemQueryService.findById(cartItem.getId())).thenReturn(cartItem);

    mockMvc.perform(get("/carts/" + user.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void IDを用いてカート情報の取得ができなかった場合() throws Exception {
    Integer wrongId = 999;
    Mockito.when(cartItemQueryService.findById(wrongId)).thenReturn(null);

    mockMvc.perform(get("/carts/" + wrongId)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
  }
}
