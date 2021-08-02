package com.example.myprojectraspi.Controller;


import Exceptions.ResourceNotFoundException;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.example.myprojectraspi.service.ShadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("http://localhost:4200")
public class ShadeController {
//    private final ShadeService shadeService;
    private final ShadeRepository shadeRepository;

    public ShadeController(ShadeRepository shadeRepository){
        this.shadeRepository = shadeRepository;
        }

        @GetMapping("/shades/allShades")
        public ResponseEntity<List<ShadeEntity>> getAllShades(){
            List<ShadeEntity> allShades = shadeRepository.findAll();
            return new ResponseEntity<>(allShades, HttpStatus.OK);

        }
        @PostMapping("/shades/allShades")
    public ResponseEntity<ShadeEntity> addShade(@RequestBody ShadeEntity shadeEntity){
        ShadeEntity newEntity = shadeRepository.save(shadeEntity);
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);

        }
        @DeleteMapping("/shades/allShades/{id1}")
    public ResponseEntity<Map<String, Boolean>> deleteEntity(@PathVariable Long id1) {
        ShadeEntity shadeEntity = shadeRepository.findById(id1)
                .orElseThrow(() -> new ResourceNotFoundException("Shade not exist with id :" + id1));
        shadeRepository.delete(shadeEntity);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
        }
        @PostMapping("/shades/allShades/o/{id1}")
    public ResponseEntity<ShadeEntity> openShade(@PathVariable Long id1) {
        ShadeEntity shadeEntity = shadeRepository.findById(id1)
                .orElseThrow(() -> new ResourceNotFoundException("Shade not exist with id : " + id1));
        ShadeService.shadeOpening();
        String id = shadeEntity.getId();
        String name = shadeEntity.getName();
        Integer addressOpen = shadeEntity.getAddressOpen();
                return new ResponseEntity<>(shadeEntity, HttpStatus.OK);
        }
}
