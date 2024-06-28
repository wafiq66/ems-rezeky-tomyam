/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ems.dao;
import com.ems.model.Report;
import com.ems.model.RestaurantManager;
/**
 *
 * @author user
 */
public interface ReportDAO {
    
    void RecordReport(Report report);
    Report RetrieveReport(RestaurantManager manager,int month,int year);
    Report RetrieveReport(Report report);
}
