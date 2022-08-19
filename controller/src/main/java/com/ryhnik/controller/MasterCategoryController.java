package com.ryhnik.controller;

import com.ryhnik.dto.master.MasterCategoryDto;
import com.ryhnik.entity.MasterCategory;
import com.ryhnik.mapper.MasterCategoryMapper;
import com.ryhnik.service.MasterCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "MasterCategory", description = "API for master categories")
public class MasterCategoryController {

    private final MasterCategoryService masterCategoryService;
    private final MasterCategoryMapper categoryMapper;

    public MasterCategoryController(MasterCategoryService masterCategoryService, MasterCategoryMapper categoryMapper) {
        this.masterCategoryService = masterCategoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping
    public ResponseEntity<MasterCategoryDto> createMasterCategory(@RequestBody MasterCategoryDto createDto) {
        MasterCategory masterCategory = masterCategoryService
                .createMasterCategory(categoryMapper.toMasterCategory(createDto));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(categoryMapper.toOutputDto(masterCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterCategoryDto> getById(@PathVariable Long id) {
        MasterCategory masterCategory = masterCategoryService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryMapper.toOutputDto(masterCategory));
    }

    @GetMapping
    public ResponseEntity<List<MasterCategoryDto>> findAll(@RequestParam(required = false, defaultValue = "") String name) {
        List<MasterCategory> masterCategories = masterCategoryService.findAll(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryMapper.toListOutputDto(masterCategories));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        masterCategoryService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterCategoryDto> updateById(@PathVariable Long id,
                                                        @RequestBody MasterCategoryDto updateDto) {
        MasterCategory masterCategory = masterCategoryService.updateById(id, updateDto.getName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryMapper.toOutputDto(masterCategory));
    }
}
