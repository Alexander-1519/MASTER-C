package com.ryhnik.exception;

public class NoSuchMasterCategoryException extends EntityNotFoundException {

    public NoSuchMasterCategoryException(Long id) {
        super(String.format("Master category not found: %s", id), Code.MASTER_CATEGORY_NOT_FOUND);
    }

    public NoSuchMasterCategoryException(String username) {
        super(String.format("Master category not found: %s", username), Code.MASTER_NOT_FOUND);
    }
}
