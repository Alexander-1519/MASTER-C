package com.ryhnik.exception;

public class NoSuchMasterRoomException extends EntityNotFoundException {

    public NoSuchMasterRoomException(Long id) {
        super(String.format("Master room not found: %s", id), Code.MASTER_ROOM_NOT_FOUND);
    }
}
