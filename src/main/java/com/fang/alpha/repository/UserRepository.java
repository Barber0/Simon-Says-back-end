package com.fang.alpha.repository;

import com.fang.alpha.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

    @Query("select new User(ur.id,ur.username,ur.email,ur.intro,ur.nickname,ur.header) from User ur where ur.username = ?1")
    User fetchUserByUsername(String username);

    @Query("select new User(ur.id,ur.username,ur.intro,ur.nickname,ur.header) from User ur where ur.id=:id")
    User fetchUserById(@Param("id") int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.nickname=:nickname, u.email=:email, u.intro=:intro, u.header=:header where u.id=:id")
    int updateUserById(
            @Param("id") int id,
            @Param("nickname") String nickname,
            @Param("email") String email,
            @Param("intro") String intro,
            @Param("header") String header);
}
