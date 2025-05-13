package in.tech_camp.shopping_api.controller.carts;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.tech_camp.shopping_api.controller.CartController;
import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.factory.CartItemFactory;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.form.CartForm;
import in.tech_camp.shopping_api.queryService.CartItemQueryService;
import in.tech_camp.shopping_api.service.CartItemService;

@WebMvcTest(CartController.class)
public class PutCartItemTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  CartItemQueryService cartItemQueryService;

  @MockBean
  CartItemService cartItemService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void カート情報更新が成功した場合() throws Exception {
    UserEntity userEntity = UserFactory.createUserEntity();
    ProductEntity productEntity = ProductFactory.createProductEntity();
    CartForm cartForm = CartItemFactory.createCartForm(userEntity, productEntity);
    Integer id = 1;
    CartItemEntity cartItemEntity = new CartItemEntity();

    Mockito.when(cartItemQueryService.findById(id)).thenReturn(cartItemEntity);
    Mockito.doNothing().when(cartItemService).updateCart(cartForm, id);

    mockMvc.perform(put("/carts/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cartForm)))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void カート情報更新が失敗した場合() throws Exception {
    UserEntity userEntity = UserFactory.createUserEntity();
    ProductEntity productEntity = ProductFactory.createProductEntity();
    CartForm cartForm = CartItemFactory.createCartForm(userEntity, productEntity);
    Integer id = 1;
    CartItemEntity cartItemEntity = new CartItemEntity();

    Mockito.when(cartItemQueryService.findById(id)).thenReturn(cartItemEntity);
    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(cartItemService).updateCart(cartForm, id);

    mockMvc.perform(put("/carts/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(cartForm)))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }
}
