package in.tech_camp.shopping_api.controller.products;

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

import in.tech_camp.shopping_api.controller.ProductController;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.form.ProductForm;
import in.tech_camp.shopping_api.queryService.ProductQueryService;
import in.tech_camp.shopping_api.service.ProductService;

@WebMvcTest(ProductController.class)
public class PutProductTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductQueryService productQueryService;

  @MockBean
  ProductService productService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void 商品情報更新が成功した場合() throws Exception {
    ProductForm productForm = ProductFactory.createProductForm();
    Integer id = 1;
    ProductEntity productEntity = new ProductEntity();

    Mockito.when(productQueryService.findById(id)).thenReturn(productEntity);
    Mockito.doNothing().when(productService).updateProduct(productForm, id);

    mockMvc.perform(put("/products/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productForm)))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void 商品情報更新が失敗した場合() throws Exception {
    ProductForm productForm = ProductFactory.createProductForm();
    Integer id = 1;
    ProductEntity productEntity = new ProductEntity();

    Mockito.when(productQueryService.findById(id)).thenReturn(productEntity);

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(productService).updateProduct(productForm, id);

    mockMvc.perform(put("/users/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productForm)))
            .andDo(print())
            .andExpect(status().isNotFound());
  }

  @Test
  void 情報を更新したい商品が存在しない場合() throws Exception {
    ProductForm productForm = ProductFactory.createProductForm();
    Integer id = 999;
    ProductEntity productEntity = new ProductEntity();

    Mockito.when(productQueryService.findById(id)).thenReturn(productEntity);

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(productService).updateProduct(productForm, id);

    mockMvc.perform(put("/users/update/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productForm)))
            .andDo(print())
            .andExpect(status().isNotFound());
  }
}
