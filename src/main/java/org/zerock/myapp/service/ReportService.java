package org.zerock.myapp.service;


import org.zerock.myapp.domain.Report;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReportService {

    // 신고 게시물 생성
    public abstract Report createReport(Report report);

    // 신고 게시물 조회
    public abstract Optional<Report> getReport(Long id);

    // 신고 게시물 목록 조회
    public abstract List<Report> getAllReports();
    
    // 신고 게시물 수정
    public abstract Report updateReport(Long id, Report reportDetails);
    
    // 신고 게시물 삭제
    public abstract Map<String, Boolean> deleteReport(Long id);

} // end interface
