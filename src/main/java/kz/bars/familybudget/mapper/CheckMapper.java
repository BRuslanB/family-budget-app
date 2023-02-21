package kz.bars.familybudget.mapper;

import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.model.Check;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CheckMapper {

    CheckDto toDto(Check check);
    Check toEntity(CheckDto checkDto);
    List<CheckDto> toDtoList(List<Check> checks);

}
