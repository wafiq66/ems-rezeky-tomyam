/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.HROfficer;
/**
 *
 * @author user
 */
public interface OfficerDAO {
    HROfficer getHROfficerById(int HROfficerID);
    HROfficer[] getAllHROfficer();
}
