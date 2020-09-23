package com.fact.mapper;

import com.fact.dto.CongeDto;
import com.fact.model.Conge;
import lombok.Data;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Date;


@Mapper(componentModel = "spring")
public interface CongeMapper {
    void updateCongeFromDto(CongeDto dto, @MappingTarget Conge connge);
}
