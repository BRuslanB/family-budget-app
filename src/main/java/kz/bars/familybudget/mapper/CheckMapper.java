package kz.bars.familybudget.mapper;

import kz.bars.familybudget.dto.CheckDto;
import kz.bars.familybudget.model.Check;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CheckMapper {

////    @Mappings(value = {
////            @Mapping(target = "budgetId", source = "budget.id"),
////            @Mapping(target = "purchaseId", source = "purchase.id")
////    })
//    CheckDto toDto(Check check);
//
////    @Mappings({
////            @Mapping(target = "budget.id", source = "budgetId"),
////            @Mapping(target = "purchase.id", source = "purchaseId")
////    })
//    Check toEntity(CheckDto checkDto);
//
//    List<CheckDto> toDtoList(List<Check> checks);

}
