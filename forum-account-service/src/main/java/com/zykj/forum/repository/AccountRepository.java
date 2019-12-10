package com.zykj.forum.repository;

import com.zykj.forum.entity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

}
