package com.fuse.canteen.repo;

import com.fuse.canteen.entity.Employee;
import com.fuse.canteen.entity.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {

    @Query(value = "select e.* from Employee e where e.id in ?1",nativeQuery = true)
    Collection<EmployeePosition> findEmployeeIn(List<Integer> employeePositions);
}
