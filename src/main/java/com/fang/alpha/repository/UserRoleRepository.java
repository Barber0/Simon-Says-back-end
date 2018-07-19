package com.fang.alpha.repository;

import com.fang.alpha.dao.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    @Override
    UserRole getOne(Integer integer);

    UserRole findByUsername(String username);

    Page<UserRole> findAll(Pageable pageable);

    @Query("select new UserRole(ur.id,ur.username ,ur.email) from UserRole ur where ur.username = ?1")
    UserRole abc(String uname);
}
