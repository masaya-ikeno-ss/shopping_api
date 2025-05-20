package in.tech_camp.shopping_api.controller.products;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import in.tech_camp.shopping_api.factory.ProductFactory;
import in.tech_camp.shopping_api.queryService.ProductQueryService;
import in.tech_camp.shopping_api.service.ProductService;

@WebMvcTest(ProductController.class)
public class GetProductsByCategoryTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  ProductQueryService productQueryService;

  @MockBean
  ProductService productService;

  @Test
  void カテゴリを用いて商品情報の取得ができた場合() throws Exception {
    Integer categoryId = 1;
    String categoryName = "にんじん";

    List<ProductDto> allProducts = ProductFactory.createProductDtoList();

    List<ProductDto> filteredProducts = allProducts.stream()
        .filter(p -> p.getCategories().contains(categoryName))
        .collect(Collectors.toList());

    Mockito.when(productQueryService.findByCategoryIdForDto(categoryId)).thenReturn(filteredProducts);

    mockMvc.perform(get("/products/category/" + categoryId)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }

  @Test
  void カテゴリを用いて商品情報の取得ができなかった場合() throws Exception {
    Integer wrongCategoryId = 99;
    Mockito.when(productQueryService.findByCategoryIdForDto(wrongCategoryId)).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/products/category/" + wrongCategoryId)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
  }
}
