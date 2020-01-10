package org.transexpress.snap.service;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transexpress.snap.dal.OrderDal;
import org.transexpress.snap.misc.Pair;
import org.transexpress.snap.misc.ResponseMessage;
import org.transexpress.snap.misc.Tuple;
import org.transexpress.snap.model.Job;
import org.transexpress.snap.model.JobPhoto;
import org.transexpress.snap.model.Order;
import org.transexpress.snap.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderDal orderDal;


    private final JobService jobService;
    private final UserService userService;
    private final JobPhotoService jobPhotoService;

    @Autowired
    public OrderService(@Qualifier("mysql_orders") OrderDal orderDal,
                        JobService jobService,
                        UserService userService, JobPhotoService jobPhotoService) {
        this.orderDal = orderDal;
        this.jobService = jobService;
        this.userService = userService;
        this.jobPhotoService = jobPhotoService;
    }

    public ResponseMessage addOrder(Order order) {

        if(orderDal.insertOrder(order) != 1){
            return new ResponseMessage("Database error", -1);
        }else return new ResponseMessage("Comanda efectuata cu succes!", 0);
    }

    public Optional<Order> getOrderByID(int id) {

        return orderDal.selectOrderByID(id);
    }

    public int deleteOrder(int id) {

        return orderDal.deleteOrderByID(id);
    }


    public List<Tuple<Job, List<JobPhoto>, User>> getAllOrdersForUserId(int userId) {
        List<Order> allOrders = orderDal.selectAllOrders();

        List<Order> allOrdersForUser = allOrders.stream()
                .filter(o -> o.getUserId() == userId)
                .collect(Collectors.toList());

        User user = userService.getUserByID(userId).get();
        User userToDisplay;

        List<Tuple<Job, List<JobPhoto>, User>> result = new ArrayList<>();

        if (user.isProvider()) {
            for (Order order : allOrders){
                Job job = jobService.getJobByID(order.getJobId()).getFirst();
                if (job.getOwnerId() == userId){
                    userToDisplay = userService.getUserByID(order.getUserId()).get();
                    result.add(new Tuple<Job, List<JobPhoto>, User>(job,
                            jobPhotoService.getAllJobPhotosForJobId(order.getJobId()),
                            userToDisplay));
                }
            }
            return result;
        }

        for(Order order : allOrdersForUser){
                userToDisplay = jobService.getJobByID(order.getJobId()).getSecond();
                result.add(new Tuple<Job, List<JobPhoto>, User>(jobService.getJobByID(order.getJobId()).getFirst(),
                        jobPhotoService.getAllJobPhotosForJobId(order.getJobId()),
                        userToDisplay));

        }

        return result;
    }

}
