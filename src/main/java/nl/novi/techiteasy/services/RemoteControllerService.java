package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.RemoteControllerCreateDTO;
import nl.novi.techiteasy.dtos.RemoteControllerPatchDTO;
import nl.novi.techiteasy.dtos.RemoteControllerResponseDTO;
import nl.novi.techiteasy.dtos.RemoteControllerUpdateDTO;
import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.mappers.RemoteControllerMapper;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;
    private final TelevisionService televisionService;

    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository, TelevisionService televisionService) {
        this.remoteControllerRepository = remoteControllerRepository;
        this.televisionService = televisionService;
    }

    public List<RemoteControllerResponseDTO> getRemoteControllers(String brand) {
        List<RemoteController> remoteControllers = (brand == null) ? remoteControllerRepository.findAll() : remoteControllerRepository.findByBrandIgnoreCase(brand);
        return RemoteControllerMapper.toDtoList(remoteControllers);
    }


    public RemoteControllerResponseDTO getRemoteControllerById(Long id) {
        RemoteController remoteController = remoteControllerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Remote Controller with ID " + id + " not found"));
        return RemoteControllerMapper.toDTO(remoteController);
    }


    public RemoteControllerResponseDTO addRemoteController(RemoteControllerCreateDTO createDto) {
        RemoteController remoteController = RemoteControllerMapper.toEntity(createDto, televisionService);

        if (createDto.getTelevisionId() != null) {
            Television television = televisionService.getTelevisionEntityById(createDto.getTelevisionId());
            remoteController.setTelevision(television);
        }

        RemoteController savedRemoteController = remoteControllerRepository.save(remoteController);
        return RemoteControllerMapper.toDTO(savedRemoteController);
    }


    public RemoteControllerResponseDTO updateRemoteController(Long id, RemoteControllerUpdateDTO updateDTO) {
        RemoteController remoteController = remoteControllerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Remote Controller with ID " + id + " not found"));

        remoteController.setBrand(updateDTO.getBrand());
        remoteController.setName(updateDTO.getName());
        remoteController.setPrice(updateDTO.getPrice());
        remoteController.setBatteryType(updateDTO.getBatteryType());
        remoteController.setIsSmart(updateDTO.getIsSmart());
        remoteController.setOriginalStock(updateDTO.getOriginalStock());
        remoteController.setSold(updateDTO.getSold());
        remoteController.setSaleDate(updateDTO.getSaleDate());

        if (updateDTO.getTelevisionId() != null) {
            Television television = televisionService.getTelevisionEntityById(updateDTO.getTelevisionId());
            remoteController.setTelevision(television);
        }

        return RemoteControllerMapper.toDTO(remoteControllerRepository.save(remoteController));
    }


    public RemoteControllerResponseDTO partialUpdateRemoteController(Long id, RemoteControllerPatchDTO patchDto) {
        RemoteController remoteController = remoteControllerRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Remote Controller with ID " + id + " not found"));

        if (patchDto.getBrand() != null) {
            remoteController.setBrand(patchDto.getBrand());
        }
        if (patchDto.getName() != null) {
            remoteController.setName(patchDto.getName());
        }
        if (patchDto.getPrice() != null) {
            remoteController.setPrice(patchDto.getPrice());
        }
        if (patchDto.getBatteryType() != null) {
            remoteController.setBatteryType(patchDto.getBatteryType());
        }
        if (patchDto.getIsSmart()) {
            remoteController.setIsSmart(patchDto.getIsSmart());
        }
        if (patchDto.getOriginalStock() != null) {
            remoteController.setOriginalStock(patchDto.getOriginalStock());
        }
        if (patchDto.getSold() != null) {
            remoteController.setSold(patchDto.getSold());
        }
        if (patchDto.getSaleDate() != null) {
            remoteController.setSaleDate(patchDto.getSaleDate());
        }
        if (patchDto.getTelevisionId() != null) {
            Television television = televisionService.getTelevisionEntityById(patchDto.getTelevisionId());
            remoteController.setTelevision(television);
        }

        return RemoteControllerMapper.toDTO(remoteControllerRepository.save(remoteController));
    }


    public void deleteRemoteController(Long id) {
        if (!remoteControllerRepository.existsById(id)) {
            throw new RecordNotFoundException("Remote Controller with ID " + id + " not found");
        }
        remoteControllerRepository.deleteById(id);
    }
}
