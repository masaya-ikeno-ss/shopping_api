package in.tech_camp.shopping_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.tech_camp.shopping_api.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
  @EntityGraph(attributePaths = {"productImages", "categories"})
  List<ProductEntity> findAllByDeletedAtIsNull();

  @EntityGraph(attributePaths = {"productImages", "categories"})
  List<ProductEntity> findByCategories_IdAndDeletedAtIsNull(Integer categoryId);
}
