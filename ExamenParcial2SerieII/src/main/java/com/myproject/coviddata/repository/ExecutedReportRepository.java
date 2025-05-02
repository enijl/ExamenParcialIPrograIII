/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author ROCK
 */
package com.myproject.coviddata.repository;
     import com.myproject.coviddata.model.ExecutedReport;
     import java.time.LocalDate;
     import java.util.Optional;
     import org.springframework.data.jpa.repository.JpaRepository;
     import org.springframework.stereotype.Repository;
     @Repository
     public interface ExecutedReportRepository extends JpaRepository<ExecutedReport, Integer> {
         Optional<ExecutedReport> findByExecutionDateAndCountryIso(LocalDate executionDate, String countryIso);
     }