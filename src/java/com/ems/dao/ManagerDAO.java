/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.RestaurantManager;
/**
 *
 * @author user
 */
public interface ManagerDAO {
        
        int getRestaurantManagerBranchId(RestaurantManager manager);
        RestaurantManager getRestaurantManagerById(int RestaurantManagerID);
        RestaurantManager[] getRestaurantManagerByBranchId(int BranchID);
        RestaurantManager[] getAllRestaurantManager();
}
