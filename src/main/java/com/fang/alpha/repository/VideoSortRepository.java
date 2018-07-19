package com.fang.alpha.repository;

import com.fang.alpha.dao.VideoSort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoSortRepository extends JpaRepository<VideoSort,Integer> {
    List<VideoSort> findByUid(int uid);

    Page<VideoSort> findByUid(int uid, Pageable pageable);

    VideoSort findById(int id);

//    @Query("select new VideoSort(vs.id,vs.name,vs.cover,vs.upper,vs.sort,vs.createAt,vs.view) from VideoSort vs")
//    Page<VideoSort> listAll(Pageable pageable);
    Page<VideoSort> findAll(Pageable pageable);
}
