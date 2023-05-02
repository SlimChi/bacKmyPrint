package fr.rt.MyPrintRed.controllers;


import fr.rt.MyPrintRed.dto.CommandeDto;
import fr.rt.MyPrintRed.dto.InsertCommandeDto;
import fr.rt.MyPrintRed.dto.InsertFullCommandeDto;
import fr.rt.MyPrintRed.services.CommandeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/commandes")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "Commandes")
public class CommandeController {

    private final CommandeService commandeService;

    @GetMapping("")
    public ResponseEntity<List<CommandeDto>> getCommandes(HttpServletRequest request){

        String uriBase = request.getRequestURL().toString();

        List<CommandeDto> commandeDtos = commandeService.getCommandes();
        for(CommandeDto commandeDto : commandeDtos){
            commandeDto.addLink("all",uriBase);
            commandeDto.addLink("self",uriBase +"/"+commandeDto.getNumeroCommande());
        }

        return ResponseEntity.ok(commandeDtos);
    }

    @GetMapping("{numeroCommande}")
    public ResponseEntity<CommandeDto> getCommandesByNumero(@PathVariable("numeroCommande")Integer numeroCommande){
        return ResponseEntity.ok(commandeService.getById(numeroCommande));
    }

    @GetMapping("utilisateur/{idUtilisateur}")
    public ResponseEntity<List<CommandeDto>> getCommandesByIdUser(@PathVariable("idUtilisateur")Integer idUtilisateur){

        return ResponseEntity.ok(commandeService.getAllByIdUtilisateur(idUtilisateur));
    }

    @PostMapping("")
    public ResponseEntity<CommandeDto> insert(@RequestBody InsertCommandeDto insertCommandeDto){
        return ResponseEntity.ok(commandeService.insert(insertCommandeDto));
    }

    @PutMapping("{numeroCommande}/{newIdStatus}")
    public ResponseEntity<CommandeDto> updateStatus(@PathVariable("numeroCommande")Integer numeroCommande,
                                       @PathVariable("newIdStatus")Integer newIdStatus){

        try{
            return ResponseEntity.ok(commandeService.updateStatus(numeroCommande,newIdStatus));
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }



    }

    @PostMapping("/full")
    public ResponseEntity<CommandeDto> insertFullCommande(@RequestBody InsertFullCommandeDto commandeDto){

        try{

            return ResponseEntity.ok(commandeService.insertFullCommande(commandeDto));

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/fullfichier")
    public ResponseEntity<CommandeDto> insertFullCommandeFichier(@RequestBody InsertFullCommandeDto commandeDto,
                                                    @RequestParam("fichiers") List<MultipartFile> fichiers){

        try{

            return ResponseEntity.ok(commandeService.insertFullCommandeFichier(commandeDto,fichiers));

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
