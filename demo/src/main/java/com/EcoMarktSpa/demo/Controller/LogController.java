package com.EcoMarktSpa.demo.Controller;



import com.EcoMarktSpa.demo.Model.LogEntry;
import com.EcoMarktSpa.demo.Service.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService service;

    public LogController(LogService service) {
        this.service = service;
    }

    @GetMapping
    public List<LogEntry> getLogs() {
        return service.getAllLogs();
    }

    @PostMapping
    public LogEntry createLog(@RequestBody LogEntry log) {
        return service.saveLog(log);
    }
}
