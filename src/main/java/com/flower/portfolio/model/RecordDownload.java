package com.flower.portfolio.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="records_downloads")
@Data
public class RecordDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String IP;
    private LocalDateTime timestamp;

    private String userAgent;
    private String downloadStatus;
}
