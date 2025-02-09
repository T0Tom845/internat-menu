package com.example.internatmenu.repository;

import com.example.internatmenu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
