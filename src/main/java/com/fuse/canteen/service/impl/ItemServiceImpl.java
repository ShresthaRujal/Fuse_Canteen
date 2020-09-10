package com.fuse.canteen.service.impl;

import com.fuse.canteen.constants.Status;
import com.fuse.canteen.dto.ItemDto;
import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.entity.Item;
import com.fuse.canteen.entity.User;
import com.fuse.canteen.mapper.AbstractItemServiceImpl;
import com.fuse.canteen.repo.ItemRepo;
import com.fuse.canteen.utils.CustomMessageSource;
import com.fuse.canteen.utils.NullAwareBeanUtilsBean;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.fuse.canteen.constants.StringConstants.MISSING_ID;
import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl extends AbstractItemServiceImpl {

    private final ItemRepo itemRepo;
    private final CustomMessageSource customMessageSource;
    private final String ModuleName = "ITEM";

    @Override
    public Item getById(Long itemId) throws Exception {
        Assert.notNull(itemId,customMessageSource.get(MISSING_ID));
        return itemRepo.findById(itemId).orElseThrow(() -> new Exception(NOT_FOUND));
    }

    @Override
    public List<Item> getItemInIds(List<Long> itemIds) throws Exception {
        return itemRepo.findItemInIds(itemIds);
    }

    @Override
    public void edit(ItemDto itemDto) throws Exception {
        try{
        Assert.notNull(itemDto.getId(),customMessageSource.get(MISSING_ID));
        Item itemDb = itemRepo.findById(itemDto.getId()).orElseThrow(() -> new Exception(NOT_FOUND));
        BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();
        beanUtilsBean.copyProperties(itemDb, getItem(itemDto));
        itemRepo.save(itemDb);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void create(ItemDto itemDto) throws Exception {
        try {
            itemRepo.save(getItem(itemDto));
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Object fetchAll() {
        return itemRepo.findAll();
    }

    @Override
    public void delete(Long itemId) throws Exception {
        Assert.notNull(itemId,customMessageSource.get(MISSING_ID));
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND,ModuleName)));
        item.setStatus(Status.DEACTIVATED.getKey());
        itemRepo.save(item);
    }

    @Override
    public Object fetchAllTodaysItem() {
        return itemRepo.findAllTodaysItem();
    }
}
