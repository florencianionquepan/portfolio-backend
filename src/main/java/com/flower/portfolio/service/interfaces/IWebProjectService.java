package com.flower.portfolio.service.interfaces;

import com.flower.portfolio.dto.WebProjectDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IWebProjectService {
    List<WebProjectDTO> projectsByPerson(Long id);
    WebProjectDTO create(WebProjectDTO dto, MultipartFile[] files, Long idPerson);
    WebProjectDTO update(WebProjectDTO dto, Long id, MultipartFile[] files);
}
