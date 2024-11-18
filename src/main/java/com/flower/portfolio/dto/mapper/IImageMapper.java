package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.ImageDTO;
import com.flower.portfolio.model.Image;

import java.util.List;

public interface IImageMapper {
    Image mapToEntity(ImageDTO dto);
    ImageDTO mapToDTO(Image entity);
    List<Image> mapToListEntities(List<ImageDTO> dtos);
    List<ImageDTO> mapToListDTO(List<Image> entities);
}
