package com.ryhnik.mapper;

import com.ryhnik.dto.master.MasterCategoryDto;
import com.ryhnik.entity.MasterCategory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MasterCategoryMapper {

    MasterCategoryDto toOutputDto(MasterCategory masterCategory);

    MasterCategory toMasterCategory(MasterCategoryDto masterCategoryDto);

    List<MasterCategoryDto> toListOutputDto(List<MasterCategory> masterCategories);
}
