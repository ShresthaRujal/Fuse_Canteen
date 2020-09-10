package com.fuse.canteen.service.impl;

import com.fuse.canteen.constants.OrderStatus;
import com.fuse.canteen.constants.Status;
import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.dto.OrderStatusPojo;
import com.fuse.canteen.entity.Order;
import com.fuse.canteen.mapper.AbstractOrderServiceImpl;
import com.fuse.canteen.repo.OrderRepo;
import com.fuse.canteen.service.ItemService;
import com.fuse.canteen.utils.CustomMessageSource;
import com.fuse.canteen.utils.NullAwareBeanUtilsBean;
import com.fuse.canteen.utils.UserDataConfig;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.fuse.canteen.constants.StringConstants.MISSING_ID;
import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;

@Service
public class OrderServiceImpl extends AbstractOrderServiceImpl {

    private final OrderRepo orderRepo;
    private final CustomMessageSource customMessageSource;
    private final UserDataConfig userDataConfig;

    public OrderServiceImpl(ItemService itemService,
                            OrderRepo orderRepo,
                            CustomMessageSource customMessageSource,
                            UserDataConfig userDataConfig) {
        super(itemService);
        this.orderRepo = orderRepo;
        this.customMessageSource = customMessageSource;
        this.userDataConfig = userDataConfig;
    }

    @Override
    public OrderDto edit(OrderDto orderDto) throws Exception {
        try{
        Assert.notNull(orderDto.getId(),customMessageSource.get(MISSING_ID));
        Order orderDb = orderRepo.findById(orderDto.getId()).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
        BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();
        beanUtilsBean.copyProperties(orderDb, getOrder(orderDto));
        orderDb = orderRepo.save(orderDb);
        return getOrderDto(orderDb);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public OrderDto create(OrderDto orderDto) throws Exception {
        try {
            Order order = getOrder(orderDto);
            order.setStatus(OrderStatus.PENDING.getKey());
            order = orderRepo.save(order);
            return getOrderDto(order);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Object getById(Long orderId) throws Exception {
        Assert.notNull(orderId,customMessageSource.get(MISSING_ID));
        return orderRepo.findById(orderId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
    }

    @Override
    public Object fetchAll() {
        return orderRepo.findAll();
    }

    @Override
    public void delete(Long orderId) throws Exception {
        Assert.notNull(orderId,customMessageSource.get(MISSING_ID));
        Order orderDb = orderRepo.findById(orderId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
        orderDb.setStatus(Status.DEACTIVATED.getKey());
        orderRepo.save(orderDb);
    }

    @Override
    public Object fetchPopular() {
        return orderRepo.findAllByPopularity();
    }

    @Override
    public Object fetchAllHistoryByUser() {
        Long userId = userDataConfig.getLoggedInUserId();
        return orderRepo.fetchAllHistoryByUser(userId);
    }

    @Override
    public void updateStatus(OrderStatusPojo orderStatusPojo) throws Exception {
        Assert.notNull(orderStatusPojo.getId(),customMessageSource.get(MISSING_ID));
        Order orderDb = orderRepo.findById(orderStatusPojo.getId()).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
        orderDb.setStatus(OrderStatus.getByKey(orderStatusPojo.getStatus()).getKey());
        orderRepo.save(orderDb);
    }

    @Override
    public Object fetchAllOrdersByTime() {
        return orderRepo.fetchAllOrdersByTime();
    }
}
