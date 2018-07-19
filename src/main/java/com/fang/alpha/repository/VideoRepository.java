package com.fang.alpha.repository;

import com.fang.alpha.dao.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    List<Video> findAllByUpper(int upper);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Video v set v.name=:name, v.description=:description, v.sort=:sort, v.view=0 where v.id=:id")
    int updateVideo(
            @Param("id") int id,
            @Param("name")String name,
            @Param("description")String description,
            @Param("sort") int sort);

    @Transactional
    @Modifying
    @Query("delete from Video v where v.id=:id")
    int deleteById(@Param("id") int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Video v set v.view=v.view+1 where v.id=:id")
    int watchVideo(@Param("id") int id);
}
