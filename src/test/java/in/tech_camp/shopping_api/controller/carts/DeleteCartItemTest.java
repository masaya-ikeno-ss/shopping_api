package in.tech_camp.shopping_api.controller.carts;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
public class DeleteCartItemTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CartItemQueryService cartItemQueryService;

  @MockBean
  CartItemService cartItemService;

  @Test
  void カート内の商品が正常に削除できた場合() throws Exception {
    UserEntity userEntity = UserFactory.createUserEntity();
    ProductEntity productEntity = ProductFactory.createProductEntity();
    CartItemEntity cartItemEntity = CartItemFactory.createCartItem(userEntity, productEntity);
    when(cartItemQueryService.findById(cartItemEntity.getId())).thenReturn(cartItemEntity);
    doNothing().when(cartItemService).deleteCart(cartItemEntity);

    mockMvc.perform(delete("/carts/" + cartItemEntity.getId())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void カート内の商品が見つからず削除できない場合() throws Exception {
    Integer wrongId = 999;
    when(cartItemQueryService.findById(wrongId)).thenReturn(null);

    mockMvc.perform(delete("/carts/" + wrongId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
