package nl.novi.TechItEasy.controllers;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private List<String> televisionDataBase = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<String>> getAllTelevisions() {
        return ResponseEntity.ok(televisionDataBase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevisionById(@PathVariable("id") int id) {
        return ResponseEntity.ok(televisionDataBase.get(id));
    }

    @PostMapping
    public ResponseEntity<String> addTelevision(@RequestBody String television) {
        televisionDataBase.add(television);
        return ResponseEntity.created(null).body("Television added: " + television);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevisionById(@PathVariable int id, @RequestBody String television) {
        return ResponseEntity.ok("Updated television (id: " + id + ") : " + television);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevisionById(@PathVariable int id) {
        televisionDataBase.set(id, null);
        return ResponseEntity.noContent().build();
    }
}
