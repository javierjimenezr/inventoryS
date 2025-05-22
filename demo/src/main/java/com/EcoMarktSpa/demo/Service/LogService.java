package com.EcoMarktSpa.demo.Service;

import com.EcoMarktSpa.demo.Model.LogEntry;
import com.EcoMarktSpa.demo.Repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogRepository repo;

    public LogService(LogRepository repo) {
        this.repo = repo;
    }

    public List<LogEntry> getAllLogs() {
        return repo.findAll();
    }

    public LogEntry saveLog(LogEntry log) {
        return repo.save(log);
    }
}
