package in.tech_camp.shopping_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.tech_camp.shopping_api.entity.ProductImageEntity;

@Repository
public interface ProductImageRepository  extends JpaRepository<ProductImageEntity, Integer> {
  List<ProductImageEntity> findByProduct_id(Integer productId);

  @Modifying
  @Transactional
  @Query("UPDATE ProductImageEntity pi SET pi.deletedAt = :now WHERE pi.product.id = :productId")
  int deleteAllByProductId(@Param("productId") Integer productId, @Param("now") LocalDateTime now);
}
