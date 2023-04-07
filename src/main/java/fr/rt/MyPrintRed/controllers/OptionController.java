package fr.rt.MyPrintRed.controllers;


import fr.rt.MyPrintRed.dto.OptionDto;
import fr.rt.MyPrintRed.services.OptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "Options")
public class OptionController {

    private final OptionService optionService;

    @GetMapping("")
    public ResponseEntity getOptions(){
        return ResponseEntity.ok(optionService.getOptions());
    }

    @GetMapping("{idOption}")
    public ResponseEntity getOptionsById(@PathVariable("idOption")Integer idOption){
        return ResponseEntity.ok(optionService.getOptionById(idOption));
    }

    @PostMapping("")
    public ResponseEntity insert(@RequestBody OptionDto optionDto){
        return ResponseEntity.ok(optionService.insert(optionDto));
    }

    @PutMapping("{idOption}")
    public ResponseEntity update(@PathVariable("idOption")Integer idOption,
                                 @RequestBody OptionDto optionDto){
        return ResponseEntity.ok(optionService.update(idOption,optionDto));
    }

    @DeleteMapping("{idOption}")
    public ResponseEntity deleteById(@PathVariable("idOption")Integer idOption){
        try{
            optionService.deleteById(idOption);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
