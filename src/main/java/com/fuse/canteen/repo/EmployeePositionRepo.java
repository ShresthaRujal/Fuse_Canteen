package com.fuse.canteen.repo;

import com.fuse.canteen.entity.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EmployeePositionRepo extends JpaRepository<EmployeePosition,Long> {

    @Query(value = "select ep.* from employeepositions ep where ep.id in ?1", nativeQuery = true)
    Collection<EmployeePosition> findEmployeePositionsIn(List<Long> employeePositionIds);
}
