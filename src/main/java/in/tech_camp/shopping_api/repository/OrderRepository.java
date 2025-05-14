package in.tech_camp.shopping_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.tech_camp.shopping_api.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>  {
  OrderEntity findByIdAndDeletedAtIsNull(Integer id);

  List<OrderEntity> findByUserIdAndDeletedAtIsNull(Integer userId);
}
