package com.ryhnik.service;

import com.ryhnik.entity.MasterCategory;
import com.ryhnik.exception.NoSuchMasterCategoryException;
import com.ryhnik.repository.MasterCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterCategoryService {

    private final MasterCategoryRepository categoryRepository;

    public MasterCategoryService(MasterCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public MasterCategory createMasterCategory(MasterCategory masterCategory) {
        return categoryRepository.save(masterCategory);
    }

    public MasterCategory findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchMasterCategoryException(id));
    }

    public List<MasterCategory> findAll(String name) {
        return categoryRepository.findAll(name);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public MasterCategory updateById(Long id, String name) {
        MasterCategory masterById = findById(id);

        masterById.setName(name);

        return categoryRepository.save(masterById);
    }
}
