package nl.novi.techiteasy.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy.dtos.RemoteControllerCreateDTO;
import nl.novi.techiteasy.dtos.RemoteControllerPatchDTO;
import nl.novi.techiteasy.dtos.RemoteControllerResponseDTO;
import nl.novi.techiteasy.dtos.RemoteControllerUpdateDTO;
import nl.novi.techiteasy.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remote-controllers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping
    public ResponseEntity<List<RemoteControllerResponseDTO>> getAllRemoteControllers(@RequestParam(value = "brand", required = false) String brand) {
        return ResponseEntity.ok(remoteControllerService.getRemoteControllers(brand));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerResponseDTO> getRemoteControllerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(remoteControllerService.getRemoteControllerById(id));
    }

    @PostMapping
    public ResponseEntity<?> addRemoteController(@Valid @RequestBody RemoteControllerCreateDTO newRemoteController,
                                                 BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.created(null).body(remoteControllerService.addRemoteController(newRemoteController));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRemoteController(@PathVariable("id") Long id,
                                                    @Valid @RequestBody RemoteControllerUpdateDTO updateDTO,
                                                    BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(remoteControllerService.updateRemoteController(id, updateDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateRemoteController(@PathVariable("id") Long id,
                                                           @Valid @RequestBody RemoteControllerPatchDTO patchDTO,
                                                           BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(remoteControllerService.partialUpdateRemoteController(id, patchDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRemoteController(@PathVariable("id") Long id) {
        remoteControllerService.deleteRemoteController(id);
        return ResponseEntity.noContent().build();
    }
}