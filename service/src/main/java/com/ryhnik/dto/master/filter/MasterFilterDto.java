package com.ryhnik.dto.master.filter;

import com.ryhnik.dto.master.MasterCategoryDto;

public class MasterFilterDto {

    private MasterCategoryDto category;
    private Long experience;

    public Long getExperience() {
        return experience;
    }

    public void setExperience(Long experience) {
        this.experience = experience;
    }

    public MasterCategoryDto getCategory() {
        return category;
    }

    public void setCategory(MasterCategoryDto
                                    category) {
        this.category = category;
    }
}
