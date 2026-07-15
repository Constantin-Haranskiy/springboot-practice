package mate.academy.springboot.practice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    @JoinColumn(nullable = false)
    private OrderStatus status;

    @JoinColumn(nullable = false)
    private BigDecimal total;
    @JoinColumn(nullable = false)
    private LocalDateTime orderDate;
    @JoinColumn(nullable = false)
    private String shippingAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<OrderItem> orderItems = new HashSet<>();
}
