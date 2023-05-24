package br.com.andre.order.msorder.integration.repository;

import br.com.andre.order.msorder.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    @Query("""
            SELECT COUNT(o.orderId)
            FROM Order o
            WHERE o.customerId = :customerId
    """)
    Integer countOrderByCustomerId(Long customerId);

    @Query("""
            SELECT SUM(i.price)
            FROM Order o
            LEFT JOIN Item i
            ON i.order.orderId = o.orderId
            WHERE o.orderId = :orderId
            AND o.customerId = :customerId
    """)
    Double getTotalValueOrderByOrderIdAndCustomerId(Long orderId, Long customerId);

    boolean existsByOrderIdAndCustomerId(Long orderId, Long customerId);

}
