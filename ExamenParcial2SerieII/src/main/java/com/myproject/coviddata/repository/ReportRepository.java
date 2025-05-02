/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myproject.coviddata.repository;

/**
 *
 * @author ROCK
 */
import com.myproject.coviddata.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
public interface ReportRepository extends JpaRepository<Report, Integer> {
    // Consulta para obtener reportes de un país en una fecha determinada
    List<Report> findByIsoAndDate(String iso, LocalDate date);
}
