package com.fuse.canteen;

import com.fuse.canteen.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.* from Users u where u.UserName = ?1 OR u.Email = ?1",nativeQuery = true)
    Optional<User> findByUserNameOrEmail(String userName);
}
