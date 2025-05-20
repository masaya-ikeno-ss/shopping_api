package in.tech_camp.shopping_api.controller.products;

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

import in.tech_camp.shopping_api.controller.ProductController;
import in.tech_camp.shopping_api.dto.ProductDto;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.queryService.ProductQueryService;
import in.tech_camp.shopping_api.service.ProductService;

@WebMvcTest(ProductController.class)
public class GetProductByIdTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductQueryService productQueryService;

  @MockBean
  ProductService productService;

  @Test
  void 商品情報の取得ができた場合() throws Exception {
    ProductDto product = ProductFactory.createProductDto();
    Mockito.when(productQueryService.findByIdForDto(product.getId())).thenReturn(product);

    mockMvc.perform(get("/products/" + product.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void 商品情報の取得ができなかった場合() throws Exception {
    ProductDto product = ProductFactory.createProductDto();
    Integer wrongId = 2;
    Mockito.when(productQueryService.findByIdForDto(wrongId)).thenReturn(null);

    mockMvc.perform(get("/products/" + wrongId)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
  }
}