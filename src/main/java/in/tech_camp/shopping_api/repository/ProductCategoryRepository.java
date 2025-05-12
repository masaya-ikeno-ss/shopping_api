package in.tech_camp.shopping_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.tech_camp.shopping_api.entity.ProductCategoryEntity;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Integer> {
  List<ProductCategoryEntity> findByProduct_id(Integer productId);

  @Modifying
  @Transactional
  @Query("UPDATE ProductCategoryEntity pc SET pc.deletedAt = :now WHERE pc.product.id = :productId")
  int logicalDeleteAllByProductId(@Param("productId") Integer productId, @Param("now") LocalDateTime now);
}