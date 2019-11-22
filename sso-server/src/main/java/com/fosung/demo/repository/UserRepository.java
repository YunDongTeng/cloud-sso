package com.fosung.demo.repository;

import com.fosung.demo.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<SysUser,Integer> {

    SysUser queryByUsername(String username);
}
