/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author ROCK
 */
package com.myproject.coviddata.model;
     import jakarta.persistence.*;
     import java.time.LocalDate;
     //seccion 4
     
     @Entity
     @Table(name = "executed_reports", uniqueConstraints = {
         @UniqueConstraint(columnNames = {"execution_date", "country_iso"})
     })
     public class ExecutedReport {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Integer id;
         //seccion 5
         
         @Column(name = "execution_date", nullable = false)
         private LocalDate executionDate;
         @Column(name = "country_iso", nullable = false, length = 3)
         private String countryIso;
         // Constructores
         public ExecutedReport() { }
         public ExecutedReport(LocalDate executionDate, String countryIso) {
             this.executionDate = executionDate;
             this.countryIso = countryIso;
         }
         public Integer getId() {
             return id;
         }
         public void setId(Integer id) {
             this.id = id;
         }
         public LocalDate getExecutionDate() {
             return executionDate;
         }
         public void setExecutionDate(LocalDate executionDate) {
             this.executionDate = executionDate;
         }
         public String getCountryIso() {
             return countryIso;
         }
         public void setCountryIso(String countryIso) {
             this.countryIso = countryIso;
         }
     }