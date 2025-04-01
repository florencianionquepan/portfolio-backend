package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.model.RecordDownload;

public interface IRecordDownloadService {
    RecordDownload create(String status, String user_agent, String ip);
}
