package org.zerock.myapp.service;


import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.Report;
import org.zerock.myapp.repository.ReportRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Log4j2
@NoArgsConstructor

@Service
public class ReportServiceImpl implements ReportService{

    @Setter(onMethod_ = @Autowired)
    private ReportRepository reportRepo;



    @Override
    public Report createReport(Report report) { // 신고 게시물 생성
        log.trace("createReport() invoked.");

        return reportRepo.save(report);
    } // createReport


    @Override
    public Optional<Report> getReport(Long id) { // 신고 게시물 조회
        log.trace("getReport() invoked.");


        return reportRepo.findById(id);
    } // getReport


    @Override
    public List<Report> getAllReports() {  // 신고 게시물 목록 조회
        log.trace("getAllReports() invoked.");

        return reportRepo.findAll();
    } // getAllReports


    @Override
    public Report updateReport(Long id, Report reportDetails) { // 신고 게시물 수정
        log.trace("updateReport() invoked.");

        Optional<Report> optionalReport = reportRepo.findById(id);
        if(optionalReport.isPresent()) {
            Report report = optionalReport.get();

            report.setContent(reportDetails.getContent());
            report.setReportStatus(reportDetails.getReportStatus());

            return reportRepo.save(report);
        } // if




        return null;
    } // updateReport


    @Override
    public Map<String, Boolean> deleteReport(Long id) {
        log.trace("deleteReport() invoked.");

        Optional<Report> optionalReport = reportRepo.findById(id);
        if(optionalReport.isPresent()) {
            Report report = optionalReport.get();

            reportRepo.delete(report);
            Map<String, Boolean> response = new HashMap<>();

            response.put("deleted", Boolean.TRUE);
            return response;
        } // if


        return null;
    } // deleteReport


} // end class
