package in.tech_camp.shopping_api.controller.products;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import in.tech_camp.shopping_api.config.SecurityConfig;
import in.tech_camp.shopping_api.controller.ProductController;
import in.tech_camp.shopping_api.dto.ProductDto;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.queryService.ProductQueryService;
import in.tech_camp.shopping_api.service.ProductService;

@WebMvcTest(ProductController.class)
@Import(SecurityConfig.class)
public class GetProductsTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductQueryService productQueryService;

  @MockBean
  ProductService productService;

  @Test
  void 商品情報の一括取得ができた場合() throws Exception {
    List<ProductDto> productList = ProductFactory.createProductDtoList();
    Mockito.when(productQueryService.findAllForDto()).thenReturn(productList);

    mockMvc.perform(get("/products/")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void 商品情報の一括取得ができなかった場合() throws Exception {
    List<ProductDto> productList = null;
    Mockito.when(productQueryService.findAllForDto()).thenReturn(productList);

    mockMvc.perform(get("/products/")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
}
