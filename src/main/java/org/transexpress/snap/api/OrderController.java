package org.transexpress.snap.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.transexpress.snap.misc.Pair;
import org.transexpress.snap.misc.ResponseMessage;
import org.transexpress.snap.misc.Tuple;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.JobPhoto;
import org.transexpress.snap.model.Order;
import org.transexpress.snap.model.User;
import org.transexpress.snap.service.OrderService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/order")
@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseMessage addOrder(@Valid @NonNull @RequestBody Order order) {
        return orderService.addOrder(order);
    }


    @GetMapping(path = "{id}")
    public Order getOrderById(@PathVariable("id") int id) {
        return orderService.getOrderByID(id).orElse(null);
    }


    @GetMapping(path = "userId/{userId}")
    public List<Tuple<Job, List<JobPhoto>, User>> getAllOrdersForUserID(@PathVariable("userId") int id) {
        return orderService.getAllOrdersForUserId(id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrderByID(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
    }

}
