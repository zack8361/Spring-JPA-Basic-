package jpabasic.ex1hellojpa.EntityEx;


import jakarta.persistence.*;
import jpabasic.ex1hellojpa.EntityEx.ItemList.Item;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long order_item_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    private int orderPrice;
    private int count;

}
