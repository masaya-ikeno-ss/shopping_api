package in.tech_camp.shopping_api.controller.products;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.tech_camp.shopping_api.controller.ProductController;
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.factory.UserFactory;
import in.tech_camp.shopping_api.form.ProductForm;
import in.tech_camp.shopping_api.form.UserForm;
import in.tech_camp.shopping_api.queryService.ProductQueryService;
import in.tech_camp.shopping_api.service.ProductService;

@WebMvcTest(ProductController.class)
public class PostProductTest {
  
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductQueryService productQueryService;

  @MockBean
  ProductService productService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void 商品登録が成功した場合() throws Exception {
    ProductForm productForm = ProductFactory.createProductForm();

    Mockito.doNothing().when(productService).registerProduct(productForm);

    mockMvc.perform(post("/products/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productForm)))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void 商品登録が失敗した場合() throws Exception {
    ProductForm productForm = ProductFactory.createProductForm();
    productForm.setProductName("");

    Mockito.doThrow(new IllegalArgumentException("Bad request")).when(productService).registerProduct(productForm);

    mockMvc.perform(post("/products/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(productForm)))
            .andDo(print())
            .andExpect(status().isBadRequest());
  }
}
