package com.example.myprojectraspi.Controller;


import Exceptions.ResourceNotFoundException;
import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import com.example.myprojectraspi.service.ShadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ShadeController {

     private final ShadeService shadeService;

    private final ShadeRepository shadeRepository;

    public ShadeController(ShadeService shadeService, ShadeRepository shadeRepository){
        this.shadeService = shadeService;
        this.shadeRepository = shadeRepository;
        }

        @GetMapping("/")
        public String home(){
            return ("<h1>Welcome</h1>");
        }

//getting list of all shades to Angular
        @GetMapping("/shades/allShades")
        public ResponseEntity<List<ShadeEntity>> getAllShades(){

            List<ShadeEntity> allShades = shadeRepository.findAll();
            return new ResponseEntity<>(allShades, HttpStatus.OK);

        }
        //Add new shade to database
        @PostMapping("/shades/allShades")
    public ResponseEntity<ShadeEntity> addShade(@RequestBody ShadeEntity shadeEntity){
        ShadeEntity newEntity = shadeRepository.save(shadeEntity);
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);

        }
        //deleting shade from database
        @DeleteMapping("/shades/allShades/{id1}")
    public ResponseEntity<Map<String, Boolean>> deleteEntity(@PathVariable Long id1) {
        ShadeEntity shadeEntity = shadeRepository.findById(id1)
                .orElseThrow(() -> new ResourceNotFoundException("Shade not exist with id :" + id1));
        shadeRepository.delete(shadeEntity);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
        }
        //moving shade (calling method move from shadeService)
        @PostMapping("/shades/allShades/move/{id1}")
        public HttpStatus move(@PathVariable Long id1, @RequestParam Integer value) throws InterruptedException {
            shadeService.moveShade(id1, value);


        return HttpStatus.ACCEPTED;
        }


}
