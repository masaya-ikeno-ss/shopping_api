package in.tech_camp.shopping_api.controller.carts;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

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
import in.tech_camp.shopping_api.queryService.UserQueryService;
import in.tech_camp.shopping_api.service.CartItemService;

@WebMvcTest(CartController.class)
public class DeleteCartItemsByUserTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CartItemQueryService cartItemQueryService;

  @MockBean
  CartItemService cartItemService;

  @MockBean
  UserQueryService userQueryService;

  @Test
  void ユーザー情報に紐づいたカート情報の全削除に成功した場合() throws Exception {
    UserEntity userEntity = UserFactory.createUserEntity();
    List<ProductEntity> productEntities = ProductFactory.createProductEntities();
    List<CartItemEntity> cartItemEntities = CartItemFactory.createCartItemsEqualsUser(userEntity, productEntities);

    when(userQueryService.getUserById(userEntity.getId())).thenReturn(userEntity);
    when(cartItemQueryService.findByUserId(userEntity.getId())).thenReturn(cartItemEntities);
    doNothing().when(cartItemService).deleteCartsByUser(userEntity);

    mockMvc.perform(delete("/carts/users/" + userEntity.getId())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void ユーザーが存在しなかった場合() throws Exception {
    Integer wrongUserId = 999;
    when(userQueryService.getUserById(wrongUserId)).thenReturn(null);

    mockMvc.perform(delete("/carts/users/" + wrongUserId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void カート情報が存在しなかった場合() throws Exception {
    UserEntity userEntity = UserFactory.createUserEntity();
    when(userQueryService.getUserById(userEntity.getId())).thenReturn(userEntity);
    when(cartItemQueryService.findByUserId(userEntity.getId())).thenReturn(null);

    mockMvc.perform(delete("/carts/users/" + userEntity.getId())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }
}
