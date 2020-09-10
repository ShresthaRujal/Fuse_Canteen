package com.fuse.canteen.repo;

import com.fuse.canteen.entity.LogDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepo extends JpaRepository<LogDb,Long> {
}
