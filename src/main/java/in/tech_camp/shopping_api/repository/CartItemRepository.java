package in.tech_camp.shopping_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.tech_camp.shopping_api.entity.CartItemEntity;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Integer> {
  List<CartItemEntity> findByUserIdAndDeletedAtIsNull(Integer userId);
}
