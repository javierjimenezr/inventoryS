package com.EcoMarktSpa.demo.Repository;


import com.EcoMarktSpa.demo.Model.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<LogEntry, Long> {}
