
package com.server.repository;

import com.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository <User,Long>{

    // User findByName(String name);
    User findByNameIgnoreCase(String name);

}