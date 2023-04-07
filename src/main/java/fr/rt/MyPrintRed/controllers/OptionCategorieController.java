package fr.rt.MyPrintRed.controllers;

import fr.rt.MyPrintRed.dto.OptionCategorieDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.services.OptionCategorieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/optioncategories")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "OptionCategories")
public class OptionCategorieController {

    private final OptionCategorieService service;

    @GetMapping("")
    public ResponseEntity getOptionsCategories(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{idCategorie}")
    public ResponseEntity getOptionsCategoriesByIdCategorie(@PathVariable("idCategorie")Integer idCategorie){
        return ResponseEntity.ok(service.getAllByIdCategorie(idCategorie));
    }

    @PostMapping("")
    public ResponseEntity insert(@RequestBody OptionCategorieDto optionCategorieDto){
        return ResponseEntity.ok(service.insert(optionCategorieDto));
    }

    @DeleteMapping("")
    public ResponseEntity delete(@RequestBody OptionCategorieDto optionCategorieDto){
        service.removeTypeOptionsFromCategorie(optionCategorieDto);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{idCategorie}")
    public ResponseEntity updateOptions(@PathVariable("idCategorie") Integer idCategorie,
                                        @RequestBody List<TypeOptionDto> typeOptionDtos){
        return ResponseEntity.ok(service.updateOptions(idCategorie,typeOptionDtos));
    }
}
