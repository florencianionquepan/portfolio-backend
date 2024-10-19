package com.flower.portfolio.dto.mapper;

import com.flower.portfolio.dto.TechnologyDTO;
import com.flower.portfolio.model.Technology;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITechnologyMapper {
    TechnologyDTO mapToDto(Technology entity);
    Technology mapToEntity(TechnologyDTO dto);
    List<TechnologyDTO> mapToListDtos(List<Technology> entities);

}
