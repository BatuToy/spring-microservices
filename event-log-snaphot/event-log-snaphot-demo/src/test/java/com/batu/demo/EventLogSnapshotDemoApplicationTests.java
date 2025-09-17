package com.batu.demo;

import com.batu.demo.domain.aggregate.LineItem;
import com.batu.demo.domain.aggregate.OrderAggregate;
import com.batu.demo.domain.vo.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class EventLogSnapshotDemoApplicationTests {

	@Test
	void contextLoads() {
	}

    @Test
    void create_order_with_line_items() {
        OrderAggregate order = new OrderAggregate(BigDecimal.valueOf(0));
        order.addItem(new LineItem("Xiaomi Poco F3", 2, BigDecimal.valueOf(30), BigDecimal.valueOf(60)));
        order.addItem(new LineItem("Apple Iphone 11 Pro", 1, BigDecimal.valueOf(55), BigDecimal.valueOf(55)));
        Assertions.assertEquals(order.getTotalPrice(), order.getItems().stream().map(LineItem::getSubTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        Assertions.assertEquals(Status.CREATED,
                order.getChange().retrieveAggregateByVersion(1).getStatus());
    }

    @Test
    void pay_order() {
        OrderAggregate order = new OrderAggregate(BigDecimal.valueOf(0));
        order.addItem(new LineItem("Xiaomi Poco F3", 2, BigDecimal.valueOf(30), BigDecimal.valueOf(60)));
        order.addItem(new LineItem("Apple Iphone 11 Pro", 1, BigDecimal.valueOf(55), BigDecimal.valueOf(55)));
        order.pay();
        Assertions.assertEquals(Status.PAID, order.getStatus());
    }

    @Test
    void deliver_order() {
    }

    @Test
    void rollback_to_empty_cart() {
        OrderAggregate order = new OrderAggregate(BigDecimal.valueOf(0));
        order.addItem(new LineItem("Xiaomi Poco F3", 2, BigDecimal.valueOf(30), BigDecimal.valueOf(60)));
        order.addItem(new LineItem("Apple Iphone 11 Pro", 1, BigDecimal.valueOf(55), BigDecimal.valueOf(55)));
        order.pay();
        order.deliver();
        // TODO= Fix the ObjectMapper default construct issue !
        OrderAggregate emptyOrder = order.getChange().retrieveAggregateByVersion(1);
        Assertions.assertEquals(Status.CREATED, emptyOrder.getStatus());
        Assertions.assertEquals(Status.DELIVERED, order.getStatus());
    }

}
