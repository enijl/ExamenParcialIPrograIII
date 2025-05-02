/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myproject.coviddata.config;
     import com.myproject.coviddata.model.ExecutedReport;
     import com.myproject.coviddata.repository.ExecutedReportRepository;
     import com.myproject.coviddata.service.RegionService;
     import com.myproject.coviddata.service.ProvinceService;
     import com.myproject.coviddata.service.ReportService;
     import java.time.LocalDate;
     import org.springframework.beans.factory.annotation.Autowired;
     import org.springframework.beans.factory.annotation.Value;
     import org.springframework.scheduling.annotation.Scheduled;
     import org.springframework.stereotype.Component;
     @Component
     public class AutoFetchTask {
         @Autowired
         private RegionService regionService;
         @Autowired
         private ProvinceService provinceService;
         @Autowired
         private ReportService reportService;
         @Autowired
         private ExecutedReportRepository executedReportRepository;
         @Value("${covid.report.date}")//<--------------------------------------------
         private String reportDateString;
         private static final String COUNTRY_ISO = "GTM";
         @Scheduled(initialDelay = 15000, fixedDelay = Long.MAX_VALUE)
         public void autoRunCovidDataLoad() {
             LocalDate reportDate = LocalDate.parse(reportDateString);
             //seccion 1
             boolean alreadyExecuted = executedReportRepository
                     .findByExecutionDateAndCountryIso(reportDate, COUNTRY_ISO)
                     .isPresent();
             //seccion 2
             if (alreadyExecuted) {
                 System.out.println("🕒 Ejecución omitida: Se ha procesado previamente el país " 
                     + COUNTRY_ISO + " para la fecha " + reportDate);//<---------------------------------------------
                 return;
             }
             System.out.println("🕒 Iniciando procesamiento para el país " + COUNTRY_ISO 
                     + " en la fecha " + reportDate);
             regionService.fetchAndSaveRegions();
             provinceService.fetchAndSaveProvinces(COUNTRY_ISO);
             reportService.fetchAndSaveReports(COUNTRY_ISO, reportDateString);
             //seccion 3
             ExecutedReport executedReport = new ExecutedReport(reportDate, COUNTRY_ISO);
             executedReportRepository.save(executedReport);
             System.out.println("✅ Proceso completado y registrado para el país " + COUNTRY_ISO 
                     + " en la fecha " + reportDate);
         }
     }