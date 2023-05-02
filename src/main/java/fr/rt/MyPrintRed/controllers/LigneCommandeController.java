package fr.rt.MyPrintRed.controllers;

import fr.rt.MyPrintRed.dto.InsertLigneCommandeDto;
import fr.rt.MyPrintRed.dto.LigneCommandeDto;
import fr.rt.MyPrintRed.services.LigneCommandeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/lignecommandes")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "LigneCommandes")
public class LigneCommandeController {

    private final LigneCommandeService service;

    @GetMapping("")
    public ResponseEntity<List<LigneCommandeDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("{numeroCommande}")
    public ResponseEntity<List<LigneCommandeDto>> getAllByNumeroCommande(@PathVariable("numeroCommande") Integer numeroCommande){
        return ResponseEntity.ok(service.getAllByNumeroCommande(numeroCommande));
    }

    @GetMapping("{numeroCommande}/{numeroLigneCommande}")
    public ResponseEntity<LigneCommandeDto> getByNumeros(@PathVariable("numeroCommande") Integer numeroCommande,
                                       @PathVariable("numeroLigneCommande")Integer numeroLigneCommande){
        return ResponseEntity.ok(service.getAllByNumeros(numeroCommande,numeroLigneCommande));
    }

    @PostMapping("")
    public ResponseEntity<LigneCommandeDto> insert(@RequestBody InsertLigneCommandeDto insertDto){
        return ResponseEntity.ok(service.insert(insertDto));
    }

    @PutMapping("{numeroCommande}/{numeroLigneCommande}/{newIdStatus}")
    public ResponseEntity<LigneCommandeDto> updateStatus(@PathVariable("numeroCommande")Integer numeroCommande,
                                       @PathVariable("numeroLigneCommande")Integer numeroLigneCommande,
                                       @PathVariable("newIdStatus")Integer newIdStatus){

        try{
            return ResponseEntity.ok(service.updateStatus(numeroCommande,numeroLigneCommande,newIdStatus));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


    }
}
