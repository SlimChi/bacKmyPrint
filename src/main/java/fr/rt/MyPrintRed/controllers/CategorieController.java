package fr.rt.MyPrintRed.controllers;

import fr.rt.MyPrintRed.dto.CategorieDto;
import fr.rt.MyPrintRed.services.CategorieService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "Categories")
public class CategorieController {

    private final CategorieService categorieService;


    @GetMapping("")
    public ResponseEntity getCategories(){
        return ResponseEntity.ok(categorieService.getCategories());
    }

    @GetMapping("{idCategorie}")
    public ResponseEntity getCategorieById(@PathVariable("idCategorie")Integer idCategorie){

        return ResponseEntity.ok(categorieService.getById(idCategorie));

    }
    @PostMapping("")
    public ResponseEntity insertCategorie(@RequestBody CategorieDto categorieDto){

        return ResponseEntity.ok(categorieService.insert(categorieDto));
    }


    @PutMapping("{idCategorie}")
    public ResponseEntity updateCategorie(@PathVariable("idCategorie")Integer idCategorie,
                                          @RequestBody CategorieDto categorieDto){

        return ResponseEntity.ok(categorieService.update(idCategorie,categorieDto));
    }

    @DeleteMapping("{idCategorie}")
    public ResponseEntity deleteMapping(@PathVariable("idCategorie")Integer idCategorie){

        try{
            categorieService.deleteById(idCategorie);
            return ResponseEntity.ok().build();

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



}
