package in.tech_camp.shopping_api.controller.products;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import in.tech_camp.shopping_api.controller.ProductController;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.queryService.ProductQueryService;
import in.tech_camp.shopping_api.service.ProductService;

@WebMvcTest(ProductController.class)
public class DeleteProductTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductQueryService productQueryService;

  @MockBean
  ProductService productService;

  @Test
  void 商品が正常に削除できた場合() throws Exception {
    ProductEntity product = ProductFactory.createProductEntity();
    when(productQueryService.findById(product.getId())).thenReturn(product);
    doNothing().when(productService).deleteProduct(product);

    mockMvc.perform(delete("/products/" + product.getId())
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  void 商品が見つからず削除できない場合() throws Exception {
    Integer wrongId = 999;
    when(productQueryService.findById(wrongId)).thenReturn(null);

    mockMvc.perform(delete("/products/" + wrongId)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
