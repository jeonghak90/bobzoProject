package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.myapp.domain.Report;
import org.zerock.myapp.service.ReportServiceImpl;

import java.util.List;
import java.util.Optional;


@Log4j2
@NoArgsConstructor


@RequestMapping("/help/*")
@Controller
public class ReportController {

    @Setter(onMethod_ = @Autowired)
    private ReportServiceImpl reportService;



//    @GetMapping("/report")
//    void goReport(){
//        log.trace("goReport() invoked.");
//
//    } // goReport

    @GetMapping("/help")
    void goHelp(){
        log.trace("goHelp() invoked.");

    } // goHelp


    @GetMapping("/report/report")
    public String listReports(Model model) {
        List<Report> reports = reportService.getAllReports();
        model.addAttribute("reports",reports);

        return "/help/report/report";
    } // listReports

    @GetMapping("/report/{id}")
    public String viewReport(@PathVariable Long id, Model model) {

        Optional<Report> optionalReport = reportService.getReport(id);
        if(optionalReport.isPresent()) {
            Report report = optionalReport.get();
            model.addAttribute("report",report);

            return "/help/report/view";
        } // if

        return null;
    } // viewReport

    @GetMapping("/report/create")
    public String createReportForm(Model model) {
        model.addAttribute("report", new Report());

        return "/help/report/create";
    } // createReportForm

    @PostMapping("/report/create")
    public String createReport(@ModelAttribute Report report) {
        reportService.createReport(report);

        return "redirect:/help/report/report";
    } // createReport


    @GetMapping("/report/{id}/edit")
    public String editReportForm(@PathVariable Long id, Model model) {

        Optional<Report> optionalReport = reportService.getReport(id);
        if(optionalReport.isPresent()) {
            Report report = optionalReport.get();
            model.addAttribute("report", report);

            return "/help/report/edit";
        } // if


        return null;
    } // editReportForm

    @PostMapping("/report/{id}/edit")
    public String updateReport(@PathVariable Long id, @ModelAttribute Report report) {
        reportService.updateReport(id,report);

        return "redirect:/report";
    } // updateReport


    @GetMapping("/report/{id}/delete")
    public String deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);

        return "redirect:/report";
    } // deleteReport



} // end class
