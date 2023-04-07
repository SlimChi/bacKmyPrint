package fr.rt.MyPrintRed.controllers;

import fr.rt.MyPrintRed.dto.OptionLigneCommandeDto;
import fr.rt.MyPrintRed.dto.TypeOptionDto;
import fr.rt.MyPrintRed.services.OptionLigneCommandeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/optionlignecommandes")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "OptionLigneCommandes")
public class OptionLigneCommandeController {

    private final OptionLigneCommandeService service;

    @GetMapping("")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{numeroCommande}/{numeroLigneCommande}")
    public ResponseEntity getAllByNumeros(@PathVariable("numeroCommande") Integer numeroCommande,
                                          @PathVariable("numeroLigneCommande")Integer numeroLigneCommande){
        return ResponseEntity.ok(service.getAllByNumeros(numeroCommande,numeroLigneCommande));
    }

    @PostMapping("")
    public ResponseEntity insert(@RequestBody OptionLigneCommandeDto optionLigneCommandeDto){
        return ResponseEntity.ok(service.insert(optionLigneCommandeDto));
    }

    @DeleteMapping("")
    public ResponseEntity delete(@RequestBody OptionLigneCommandeDto optionLigneCommandeDto){
        try{
            service.delete(optionLigneCommandeDto);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping("{numeroCommande}/{numeroLigneCommande}")
    public ResponseEntity updateTypeOptions(@PathVariable("numeroCommande") Integer numeroCommande,
                                            @PathVariable("numeroLigneCommande")Integer numeroLigneCommande,
                                            @RequestBody List<TypeOptionDto> typeOptionDtos){

        return ResponseEntity.ok(service.updateOptions(numeroCommande,numeroLigneCommande,typeOptionDtos));

    }

}
