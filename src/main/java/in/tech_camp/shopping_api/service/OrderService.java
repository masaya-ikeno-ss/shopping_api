package in.tech_camp.shopping_api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import in.tech_camp.shopping_api.entity.CartItemEntity;
import in.tech_camp.shopping_api.entity.OrderEntity;
import in.tech_camp.shopping_api.entity.OrderItemEntity;
import in.tech_camp.shopping_api.entity.ProductEntity;
import in.tech_camp.shopping_api.entity.UserEntity;
import in.tech_camp.shopping_api.entity.enums.OrderStatus;
import in.tech_camp.shopping_api.entity.enums.PaymentMethod;
import in.tech_camp.shopping_api.repository.OrderItemRepository;
import in.tech_camp.shopping_api.repository.OrderRepository;
import in.tech_camp.shopping_api.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderService {
  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ProductRepository productRepository;

    public OrderService(
      OrderItemRepository orderItemRepository, 
      OrderRepository orderRepository, 
      ProductRepository productRepository
      ) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void registerOrder(
    PaymentMethod paymentMethod,
    List<CartItemEntity> cartItemEntities, 
    UserEntity user) {
      Integer totalPrice = 0;
      List<OrderItemEntity> items = new ArrayList<>();
      List<ProductEntity> products = new ArrayList<>();

      OrderEntity order = new OrderEntity();
      order.setUser(user);
      order.setStatus(OrderStatus.ORDERED);
      order.setPaymentMethod(paymentMethod);
      order.setCreatedAt(LocalDateTime.now());

      for (CartItemEntity cartItemEntity : cartItemEntities) {
        ProductEntity product = cartItemEntity.getProduct();
        if (cartItemEntity.getQuantity() > product.getStockQuantity()) {
          throw new IllegalArgumentException(product.getProductName() + "の在庫が不足しています");
        }
        Integer subTotal = cartItemEntity.getQuantity() * product.getPrice();

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setOrder(order);
        orderItemEntity.setProduct(product);
        orderItemEntity.setQuantity(cartItemEntity.getQuantity());
        orderItemEntity.setTotalPrice(subTotal);
        orderItemEntity.setCreatedAt(LocalDateTime.now());

        items.add(orderItemEntity);
        totalPrice += subTotal;

        product.setStockQuantity(product.getStockQuantity() - cartItemEntity.getQuantity());
        products.add(product);
      }

      order.setTotalPrice(totalPrice);

      orderRepository.save(order);
      orderItemRepository.saveAll(items);
      productRepository.saveAll(products);
    }

    @Transactional
    public void updatePaidStatus(OrderEntity orderEntity) {
      if (orderEntity.getStatus() != OrderStatus.ORDERED) {
        throw new IllegalArgumentException("支払い可能なステータスではありません");
      }
      orderEntity.setStatus(OrderStatus.PAID);
      orderEntity.setUpdatedAt(LocalDateTime.now());

      orderRepository.save(orderEntity);
    }

    @Transactional
    public void updateShippingStatus(OrderEntity orderEntity) {
      if (orderEntity.getStatus() != OrderStatus.PAID) {
        throw new IllegalArgumentException("発送準備可能なステータスではありません");
      }
      orderEntity.setStatus(OrderStatus.SHIPPING);
      orderEntity.setUpdatedAt(LocalDateTime.now());

      orderRepository.save(orderEntity);
    }

    @Transactional
    public void updateShippedStatus(OrderEntity orderEntity) {
      if (orderEntity.getStatus() != OrderStatus.SHIPPING) {
        throw new IllegalArgumentException("発送可能なステータスではありません");
      }
      orderEntity.setStatus(OrderStatus.SHIPPED);
      orderEntity.setUpdatedAt(LocalDateTime.now());

      orderRepository.save(orderEntity);
    }

    @Transactional
    public void updateDeliveredStatus(OrderEntity orderEntity) {
      if (orderEntity.getStatus() != OrderStatus.SHIPPED) {
        throw new IllegalArgumentException("商品お届け可能なステータスではありません");
      }
      orderEntity.setStatus(OrderStatus.DELIVERED);
      orderEntity.setUpdatedAt(LocalDateTime.now());

      orderRepository.save(orderEntity);
    }
}
