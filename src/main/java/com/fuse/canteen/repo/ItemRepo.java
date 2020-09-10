package com.fuse.canteen.repo;

import com.fuse.canteen.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepo extends JpaRepository<Item,Long> {

    @Query(value = "select itm.* from items itm where itm.id in ?1",nativeQuery = true)
    List<Item> findItemInIds(List<Long> itemIds);

    @Query(value = "SELECT * FROM items WHERE DATE(`EntryDate`) = CURDATE()",nativeQuery = true)
    List<Item> findAllTodaysItem();
}
