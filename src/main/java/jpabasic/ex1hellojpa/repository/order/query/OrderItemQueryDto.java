package jpabasic.ex1hellojpa.repository.order.query;


import lombok.Data;

@Data
public class OrderItemQueryDto {
    private String itemName;
    private int orderPrice;
    private int count;

}
