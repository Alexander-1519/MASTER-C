package com.ryhnik.controller;

import com.dropbox.core.DbxException;
import com.ryhnik.dto.master.MasterFullInputCreateDto;
import com.ryhnik.dto.master.MasterFullOutputDto;
import com.ryhnik.entity.Master;
import com.ryhnik.mapper.MasterMapper;
import com.ryhnik.service.MasterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/masters", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Master", description = "API for masters")
public class MasterController {

    private final MasterService masterService;
    private final MasterMapper masterMapper;

    public MasterController(MasterService masterService, MasterMapper masterMapper) {
        this.masterService = masterService;
        this.masterMapper = masterMapper;
    }

    @PutMapping("/{id}")
    public ResponseEntity<MasterFullOutputDto> updateById(@PathVariable Long id,
                                                          @RequestPart(value = "createDto") MasterFullInputCreateDto createDto,
                                                          @RequestPart(value = "images", required = false) List<MultipartFile> images) {
        Master master = masterService.updateInfo(id, createDto, images);
        Master byId = masterService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(masterMapper.toFullOutputDto(byId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        masterService.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterFullOutputDto> getById(@PathVariable Long id) {
        Master master = masterService.getById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(masterMapper.toFullOutputDto(master));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MasterFullOutputDto> save(
            @PathVariable Long id,
            @RequestPart(value = "createDto") MasterFullInputCreateDto createDto,
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            Principal principal) {
        Master master = masterService.updateInfo(id, createDto, images);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(masterMapper.toFullOutputDto(master));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> changePhoto(@PathVariable Long id,
                                              @RequestPart MultipartFile image) throws IOException, DbxException {
        String imageUrl = masterService.saveImage(id, image);

        return ResponseEntity.status(HttpStatus.OK)
                .body(imageUrl);
    }
}
