package com.lv.dt_server.dao;

import com.lv.dt_server.entiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserNameAndPassword(String userName, String password);

}
