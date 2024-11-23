package nl.novi.TechItEasy.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    @GetMapping
    public ResponseEntity<String> getAllTelevisions() {
        return ResponseEntity.ok("All televisions");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevisionById(@PathVariable("id") int id) {
        return ResponseEntity.ok("Television with id: " + id);
    }

    @PostMapping
    public ResponseEntity<String> addTelevision(@RequestBody String television) {
        return ResponseEntity.created(null).body("Television added: " + television);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTelevisionById(@PathVariable int id, @RequestBody String television) {
        return ResponseEntity.ok("Updated television (id: " + id + ") : " + television);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTelevisionById(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }
}
