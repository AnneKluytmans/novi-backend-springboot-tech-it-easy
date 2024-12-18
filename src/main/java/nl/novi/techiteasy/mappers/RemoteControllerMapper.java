package nl.novi.techiteasy.mappers;

import nl.novi.techiteasy.dtos.RemoteControllerCreateDTO;
import nl.novi.techiteasy.dtos.RemoteControllerResponseDTO;
import nl.novi.techiteasy.models.RemoteController;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.services.TelevisionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RemoteControllerMapper {

    private final TelevisionService televisionService;

    public RemoteControllerMapper(TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    public static RemoteControllerResponseDTO toDTO(RemoteController remoteController) {
        RemoteControllerResponseDTO dto = new RemoteControllerResponseDTO();
        dto.setId(remoteController.getId());
        dto.setBrand(remoteController.getBrand());
        dto.setName(remoteController.getName());
        dto.setPrice(remoteController.getPrice());
        dto.setBatteryType(remoteController.getBatteryType());
        dto.setIsSmart(remoteController.getIsSmart());
        dto.setOriginalStock(remoteController.getOriginalStock());
        dto.setSold(remoteController.getSold());
        dto.setSaleDate(remoteController.getSaleDate());
        dto.setTelevisionId(remoteController.getTelevision().getId());
        return dto;
    }

    public static List<RemoteControllerResponseDTO> toDtoList(List<RemoteController> remoteControllers) {
        return remoteControllers.stream().map(RemoteControllerMapper::toDTO).collect(Collectors.toList());
    }

    public static RemoteController toEntity(RemoteControllerCreateDTO createDTO, TelevisionService televisionService) {
        RemoteController remoteController = new RemoteController();
        remoteController.setId(remoteController.getId());
        remoteController.setBrand(remoteController.getBrand());
        remoteController.setName(remoteController.getName());
        remoteController.setPrice(remoteController.getPrice());
        remoteController.setBatteryType(remoteController.getBatteryType());
        remoteController.setIsSmart(remoteController.getIsSmart());
        remoteController.setOriginalStock(remoteController.getOriginalStock());
        remoteController.setSold(remoteController.getSold());
        remoteController.setSaleDate(remoteController.getSaleDate());

        if (createDTO.getTelevisionId() != null) {
            Television television = televisionService.getTelevisionEntityById(createDTO.getTelevisionId());
            remoteController.setTelevision(television);
        }

        return remoteController;
    }
}
