package org.transexpress.snap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.OrderDal;
import org.transexpress.snap.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderDal orderDal;

    @Autowired
    public OrderService(@Qualifier("mysql_orders") OrderDal orderDal) {
        this.orderDal = orderDal;
    }

    public int addOrder(Order order) {

        return orderDal.insertOrder(order);
    }

    public Optional<Order> getOrderByID(int id) {

        return orderDal.selectOrderByID(id);
    }

    public int deleteOrder(int id) {

        return orderDal.deleteOrderByID(id);
    }


    public List<Order> getAllOrdersForUserId(int userId) {
        List<Order> allOrders = orderDal.selectAllOrders();

        List<Order> result = allOrders.stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());
        return result;
    }

}
