package com.example.internatmenu.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class MenuCostDto {
    private Long menuId;
    private Date dateMenu;
    private BigDecimal totalCost;
    private BigDecimal averageCost;

    public MenuCostDto(Long menuId, Date dateMenu, BigDecimal totalCost, BigDecimal averageCost) {
        this.menuId = menuId;
        this.dateMenu = dateMenu;
        this.totalCost = totalCost;
        this.averageCost = averageCost;
    }
}