package com.fang.alpha.repository;

import com.fang.alpha.dao.DmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DmUserRepository extends JpaRepository<DmUser,Integer> {
    @Query("select du.time,du.type,du.color,du.nickname,du.text from DmUser du where du.id=:vid")
    List<DmUser> list(@Param("vid") int vid);
}
