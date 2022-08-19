package com.ryhnik.service;

import com.dropbox.core.DbxException;
import com.ryhnik.dto.master.MasterFullInputCreateDto;
import com.ryhnik.dto.master.filter.MasterFilterDto;
import com.ryhnik.entity.Master;
import com.ryhnik.exception.Code;
import com.ryhnik.exception.ExceptionBuilder;
import com.ryhnik.exception.MasterClubException;
import com.ryhnik.exception.NoSuchMasterException;
import com.ryhnik.mapper.MasterMapper;
import com.ryhnik.repository.MaintenanceDateRepository;
import com.ryhnik.repository.MaintenanceRepository;
import com.ryhnik.repository.MasterRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class MasterService {

    private final MasterRepository masterRepository;
    private final MaintenanceService maintenanceService;
    private final MaintenanceDateService maintenanceDateService;
    private final MasterReviewService masterReviewService;
    private final PortfolioImageService portfolioImageService;
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceDateRepository maintenanceDateRepository;
    private final MasterMapper masterMapper;
    private final DropBoxService dropBoxService;

    public MasterService(MasterRepository masterRepository,
                         MaintenanceService maintenanceService,
                         MaintenanceDateService maintenanceDateService,
                         MasterReviewService masterReviewService,
                         PortfolioImageService portfolioImageService,
                         MaintenanceRepository maintenanceRepository,
                         MaintenanceDateRepository maintenanceDateRepository,
                         MasterMapper masterMapper,
                         DropBoxService dropBoxService) {
        this.masterRepository = masterRepository;
        this.maintenanceService = maintenanceService;
        this.maintenanceDateService = maintenanceDateService;
        this.masterReviewService = masterReviewService;
        this.portfolioImageService = portfolioImageService;
        this.maintenanceRepository = maintenanceRepository;
        this.maintenanceDateRepository = maintenanceDateRepository;
        this.masterMapper = masterMapper;
        this.dropBoxService = dropBoxService;
    }

    public Page<Master> findAll(MasterFilterDto filter, Pageable pageable) {
        return masterRepository.findAll(LocalDate.ofEpochDay(filter.getExperience()), pageable);
    }

    public void deleteById(Long masterId) {
        boolean existsById = masterRepository.existsById(masterId);
        if (!existsById) {
            throw ExceptionBuilder.builder(Code.MASTER_EXCEPTION)
                    .withMessage("Can't find master with id = " + masterId)
                    .build(MasterClubException.class);
        }

        masterRepository.deleteById(masterId);
    }

    public String saveImage(Long masterId, MultipartFile image) throws IOException, DbxException {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new NoSuchMasterException(masterId));

        String imageUrl = dropBoxService.saveImage(image);

        master.setImageUrl(imageUrl);
        Master savedMaster = masterRepository.save(master);

        return savedMaster.getImageUrl();
    }

    public Master saveMasterInfo(Master createMaster, List<MultipartFile> images, String username, Long masterId) {
        Master master = masterRepository.findMasterByUsername(username)
                .orElseThrow(() -> new NoSuchMasterException(username));

//        master.setInfo(createMaster.getInfo());
//        master.setStartedAt(createMaster.getStartedAt());

//        List<Maintenance> maintenances = createMaster.getMaintenances().stream()
//                .peek(m -> m.setMaster(master)).collect(Collectors.toList());
//        maintenanceService.createAll(maintenances);
//
//        List<MaintenanceDate> dates = createMaster.getDates().stream()
//                .peek(m -> m.setMaster(master)).collect(Collectors.toList());
//        maintenanceDateService.createAll(dates);

        portfolioImageService.create(images, username);

        Master savedMaster = masterRepository.save(master);
        return getById(savedMaster.getId());
    }

    public Master getById(Long userId) {
        Master master = masterRepository.findById(userId)
                .orElseThrow(() -> new NoSuchMasterException(userId));

//        List<PortfolioImage> allImagesByMasterId = portfolioImageService.getAllImagesByMasterId(userId);
//        master.setImages(allImagesByMasterId);
//
//        List<MasterReview> reviews = masterReviewService.getAllByUserId(userId);
//        master.setReviews(reviews);
//
//        List<MaintenanceDate> maintenanceDates = maintenanceDateService.getAllByUserId(userId);
//        master.setDates(maintenanceDates);
//
//        List<Maintenance> maintenances = maintenanceService.getAllByUserId(userId);
//        master.setMaintenances(maintenances);

        return master;
    }

    //TODO set startedAt and change return
    @Transactional
    public Master updateInfo(Long masterId, MasterFullInputCreateDto updateDto, List<MultipartFile> images) {
        Master master = masterRepository.findById(masterId)
                .orElseThrow(() -> new NoSuchMasterException(masterId));

        Master updatedMaster = masterMapper.toMaster(updateDto);

        updatedMasterInfo(master, updatedMaster);

//        for (Maintenance maintenance : master.getMaintenances()) {
//            maintenance.setMaster(master);
//        }
//        for (MaintenanceDate date : master.getDates()) {
//            date.setMaster(master);
//        }
//        for (MasterReview review : master.getReviews()) {
//            review.setMaster(master);
//        }

        Master savedMaster = masterRepository.save(master);

        maintenanceRepository.deleteAllById(updateDto.getMaintenancesToDelete());
        maintenanceDateRepository.deleteAllById(updateDto.getDatesToDelete());

        return savedMaster;
    }

    private void updatedMasterInfo(Master masterToUpdate, Master updatedMaster) {
//        if (updatedMaster.getInfo() != null ) {
//            masterToUpdate.setInfo(updatedMaster.getInfo());
//        }
//        masterToUpdate.setDates(updatedMaster.getDates());
//        masterToUpdate.setMaintenances(updatedMaster.getMaintenances());
//        masterToUpdate.setReviews(updatedMaster.getReviews());
    }

    public Master findMasterById(Long id) {
        return masterRepository.findById(id)
                .orElseThrow(() -> new NoSuchMasterException(id));
    }

}
