package com.zykj.forum.respository;

import com.zykj.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    @Modifying
    @Query("update User user  set user.picUrl =:picUrl  where user.id =:id")
    int updateAvatar(@Param("id") long id,@Param("picUrl") String picUrl);

}
