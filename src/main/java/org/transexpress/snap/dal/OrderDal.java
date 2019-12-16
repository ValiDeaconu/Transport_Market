package org.transexpress.snap.dal;

import org.transexpress.snap.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDal {
    int insertOrder(Order order);

    List<Order> selectAllOrders();

    Optional<Order> selectOrderByID(int id);

    int deleteOrderByID(int id);

    int updateOrderByID(int id, Order order);
}
