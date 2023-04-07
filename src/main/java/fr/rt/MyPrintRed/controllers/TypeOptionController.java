package fr.rt.MyPrintRed.controllers;


import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.entities.TypeOption;
import fr.rt.MyPrintRed.services.TypeOptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/typeoptions")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "TypeOptions")
public class TypeOptionController {

    private final TypeOptionService service;

    @GetMapping("")
    public ResponseEntity getTypeOptions(){
        return ResponseEntity.ok(service.getTypeOptions());
    }

    @GetMapping("{idOption}/{idTypeOption}")
    public ResponseEntity getTypeOptionsById(@PathVariable("idOption")Integer idOption,
                                             @PathVariable("idTypeOption")Integer idTypeOption){
        return ResponseEntity.ok(service.getById(idOption,idTypeOption));
    }

    @PostMapping("")
    public ResponseEntity insert(@RequestBody TypeOptionDto typeOptionDto){
        return ResponseEntity.ok(service.insert(typeOptionDto));
    }

    @PutMapping("{idOption}/{idTypeOption}")
    public ResponseEntity update(@PathVariable("idOption")Integer idOption,
                                 @PathVariable("idTypeOption")Integer idTypeOption,
                                 @RequestBody TypeOptionDto typeOptionDto){
        return ResponseEntity.ok(service.update(idOption,idTypeOption,typeOptionDto));
    }

    @DeleteMapping("{idOption}/{idTypeOption}")
    public ResponseEntity deleteById(@PathVariable("idOption")Integer idOption,
                                     @PathVariable("idTypeOption")Integer idTypeOption){

        try {
            service.deleteById(idOption,idTypeOption);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

    }
}
