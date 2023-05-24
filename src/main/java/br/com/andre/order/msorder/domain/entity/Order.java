package br.com.andre.order.msorder.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = "items")
public class Order {

    @Id
    private Long orderId;

    private Long customerId;

    @OneToMany(targetEntity = Item.class,
            mappedBy = "order", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true
    )
    private List<Item> items;

    public Integer getQuantityItems() {
        Long quantity = items.stream().count();

        return quantity.intValue();
    }

    public Double getTotalOrder() {
        return items.stream()
                .mapToDouble(Item::getPrice)
                .sum();
    }

}
