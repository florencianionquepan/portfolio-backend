package com.flower.portfolio.service.implementations;

import com.flower.portfolio.model.RecordDownload;
import com.flower.portfolio.repository.IRecordDownloadRepository;
import com.flower.portfolio.service.interfaces.IRecordDownloadService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RecordDownloadService implements IRecordDownloadService {

    private final IRecordDownloadRepository repo;

    public RecordDownloadService(IRecordDownloadRepository repo) {
        this.repo = repo;
    }

    @Override
    public RecordDownload create(String status, String user_agent, String ip) {
        RecordDownload record=new RecordDownload();
        record.setIP(ip);
        record.setTimestamp(LocalDateTime.now());
        record.setUserAgent(user_agent);
        record.setDownloadStatus(status);
        return this.repo.save(record);
    }
}
