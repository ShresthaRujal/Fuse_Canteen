package com.fuse.canteen.repo;

import com.fuse.canteen.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query(value = "select o.* from orders o order by o.hitCount desc ",nativeQuery = true)
    List<Order> findAllByPopularity();
}
