package com.fuse.canteen.repo;

import com.fuse.canteen.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FoodRepo extends JpaRepository<Food,Long> {

    @Query(value = "select f.* from foods f order by f.hitCount desc",nativeQuery = true)
    Object fetchByPopularity();
}
