package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.ImageDTO;
import com.flower.portfolio.model.Image;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper implements IImageMapper{

    @Override
    public Image mapToEntity(ImageDTO dto) {
        Image image=new Image();
        image.setId(dto.getId());
        image.setImageId(dto.getImageId());
        image.setName(dto.getName());
        image.setUrl(dto.getUrl());
        image.setUrl(dto.getUrl());
        return image;
    }

    @Override
    public ImageDTO mapToDTO(Image entity) {
        ImageDTO dto=new ImageDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUrl(entity.getUrl());
        dto.setImageId(entity.getImageId());
        return dto;
    }

    @Override
    public List<Image> mapToListEntities(List<ImageDTO> dtos) {
        return dtos.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImageDTO> mapToListDTO(List<Image> entities) {
        return entities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}
