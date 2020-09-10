package com.fuse.canteen.repo;

import com.fuse.canteen.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query(value = "select o.* from orders o order by o.hitCount desc ",nativeQuery = true)
    List<Order> findAllByPopularity();

    @Query(value = "select o.* from orders o where o.EntryUserId = ?1 order by EntryDate desc", nativeQuery = true)
    List<Order> fetchAllHistoryByUser(Long userId);

    @Query(value = "select o.* from orders o where o.scheduleAt < current_time order by o.scheduleAt desc",nativeQuery = true)
    Object fetchAllOrdersByTime();
}
