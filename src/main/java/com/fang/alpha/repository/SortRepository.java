package com.fang.alpha.repository;

import com.fang.alpha.dao.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SortRepository extends JpaRepository<Sort,Integer> {
    List<Sort> findAll();
}
