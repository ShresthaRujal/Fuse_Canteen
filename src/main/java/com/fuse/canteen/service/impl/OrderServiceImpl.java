package com.fuse.canteen.service.impl;

import com.fuse.canteen.dto.OrderDto;
import com.fuse.canteen.entity.Order;
import com.fuse.canteen.mapper.AbstractOrderServiceImpl;
import com.fuse.canteen.repo.OrderRepo;
import com.fuse.canteen.service.ItemService;
import com.fuse.canteen.utils.CustomMessageSource;
import com.fuse.canteen.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.fuse.canteen.constants.StringConstants.MISSING_ID;
import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;

@Service
public class OrderServiceImpl extends AbstractOrderServiceImpl {

    private final OrderRepo orderRepo;
    private final CustomMessageSource customMessageSource;

    public OrderServiceImpl(ItemService itemService,
                            OrderRepo orderRepo,
                            CustomMessageSource customMessageSource) {
        super(itemService);
        this.orderRepo = orderRepo;
        this.customMessageSource = customMessageSource;
    }

    @Override
    public void edit(OrderDto orderDto) throws Exception {
        Assert.notNull(orderDto.getId(),customMessageSource.get(MISSING_ID));
        Order orderDb = orderRepo.findById(orderDto.getId()).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
        BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();
        beanUtilsBean.copyProperties(orderDb, getOrder(orderDto));
        orderRepo.save(orderDb);
    }

    @Override
    public void create(OrderDto orderDto) {
        orderRepo.save(getOrder(orderDto));
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
        orderRepo.delete(orderDb);
    }

    @Override
    public Object fetchPopular() {
        return orderRepo.findAllByPopularity();
    }
}
