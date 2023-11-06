package org.zerock.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.myapp.domain.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {

} // end interface
