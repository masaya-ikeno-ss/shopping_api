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
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.queryService.ProductQueryService;

@WebMvcTest(ProductController.class)
public class GetProductsTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductQueryService productQueryService;

  @Test
  void 商品情報の一括取得ができた場合() throws Exception {
    List<ProductEntity> productList = ProductFactory.createProductEntities();
    Mockito.when(productQueryService.findAll()).thenReturn(productList);

    mockMvc.perform(get("/products/")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void 商品情報の一括取得ができなかった場合() throws Exception {
    List<ProductEntity> productList = null;
    Mockito.when(productQueryService.findAll()).thenReturn(productList);

    mockMvc.perform(get("/products/")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound());
  }
}
