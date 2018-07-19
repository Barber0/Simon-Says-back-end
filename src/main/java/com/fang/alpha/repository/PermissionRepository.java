package com.fang.alpha.repository;

import com.fang.alpha.dao.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Role,Integer> {
}
