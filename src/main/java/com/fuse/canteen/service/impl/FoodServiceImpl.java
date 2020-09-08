package com.fuse.canteen.service.impl;

import com.fuse.canteen.dto.FoodDto;
import com.fuse.canteen.dto.UserDto;
import com.fuse.canteen.entity.Food;
import com.fuse.canteen.mapper.AbstractFoodServiceImpl;
import com.fuse.canteen.repo.FoodRepo;
import com.fuse.canteen.service.FoodService;
import com.fuse.canteen.service.ItemService;
import com.fuse.canteen.utils.CustomMessageSource;
import com.fuse.canteen.utils.NullAwareBeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.fuse.canteen.constants.StringConstants.MISSING_ID;
import static com.fuse.canteen.constants.StringConstants.NOT_FOUND;

@Service
public class FoodServiceImpl extends AbstractFoodServiceImpl {

    private final FoodRepo foodRepo;
    private final CustomMessageSource customMessageSource;

    public FoodServiceImpl(ItemService itemService,
                           FoodRepo foodRepo,
                           CustomMessageSource customMessageSource) {
        super(itemService);
        this.foodRepo = foodRepo;
        this.customMessageSource = customMessageSource;
    }

    @Override
    public void create(FoodDto foodDto) {
        foodRepo.save(getFood(foodDto));
    }

    @Override
    public void edit(FoodDto foodDto) throws Exception {
        Assert.notNull(foodDto.getId(),customMessageSource.get(MISSING_ID));
        Food foodDb = foodRepo.findById(foodDto.getId()).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
        BeanUtilsBean beanUtilsBean = new NullAwareBeanUtilsBean();
        beanUtilsBean.copyProperties(foodDb,getFood(foodDto));
        foodRepo.save(foodDb);
    }

    @Override
    public Object getById(Long foodId) throws Exception {
        Assert.notNull(foodId,customMessageSource.get(MISSING_ID));
        return foodRepo.findById(foodId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
    }

    @Override
    public Object fetchAll() {
        return foodRepo.findAll();
    }

    @Override
    public void delete(Long foodId) throws Exception {
        Assert.notNull(foodId,customMessageSource.get(MISSING_ID));
        Food food = foodRepo.findById(foodId).orElseThrow(() -> new Exception(customMessageSource.get(NOT_FOUND)));
        foodRepo.delete(food);
    }

    @Override
    public Object fetchPopular() {
        return foodRepo.fetchByPopularity();
    }
}
