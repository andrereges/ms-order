package br.com.andre.order.msorder.integration.repository;

import br.com.andre.order.msorder.domain.entity.Item;
import br.com.andre.order.msorder.domain.entity.Order;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    private Order orderData;

    @BeforeEach()
    void setup() {
        orderData = saveOrder();
    }

    @Test
    void shouldInjectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(orderRepository).isNotNull();
    }

    @Test
    void shouldSaveWhenSuccessful() {
        assertThat(orderData)
                .isNotNull();

        assertThat(orderData.getOrderId())
                .isEqualTo(1001L);

        assertThat(orderData.getCustomerId())
                .isEqualTo(1L);

        assertThat(orderData.getItems())
                .isNotEmpty();

        assertThat(orderData.getItems().size())
                .isEqualTo(2);
    }

    @Test
    void shouldReturnQuantityOrderByCustomerIdWhenExistsOrderInDatabase() {
        var quantityOrder = orderRepository.countOrderByCustomerId(1L);

        assertThat(quantityOrder)
                .isEqualTo(1);
    }

    @Test
    void shouldReturnTotalValueOrderByCustomerIdWhenExistsOrderInDatabase() {
        var totalValue = orderRepository.getTotalValueOrderByOrderIdAndCustomerId(1001L, 1L);

        assertThat(totalValue)
                .isEqualTo(6.15);
    }

    private Order saveOrder() {
        var order = Order.builder()
                .orderId(1001L)
                .customerId(1L)
                .build();

        var item = Item.builder()
                .itemId(1L)
                .name("Caneta")
                .quantity(25)
                .price(3.40)
                .order(order)
                .build();

        var item2 = Item.builder()
                .itemId(2L)
                .name("Lápis")
                .quantity(30)
                .price(2.75)
                .order(order)
                .build();

        order.setItems(List.of(item, item2));
        item.setOrder(order);

        return orderRepository.save(order);
    }

}
