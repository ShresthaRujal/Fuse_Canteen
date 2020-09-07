package com.fuse.canteen.repo;

import com.fuse.canteen.entity.EmployeePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePositionRepo extends JpaRepository<EmployeePosition,Long> {
}
