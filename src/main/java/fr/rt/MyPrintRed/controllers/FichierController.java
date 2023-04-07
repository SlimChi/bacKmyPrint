package fr.rt.MyPrintRed.controllers;


import fr.rt.MyPrintRed.dto.ResponseFile;
import fr.rt.MyPrintRed.dto.ResponseMessage;
import fr.rt.MyPrintRed.entities.Fichier;
import fr.rt.MyPrintRed.services.FichierService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static fr.rt.MyPrintRed.controllers.BaseUrl.FRONT_BASE_URL;

@RestController
@RequestMapping("/fichiers")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "Fichiers")
public class FichierController {


    private final FichierService storageService;

    @PostMapping("")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("")
    public ResponseEntity getFichiers(){
        return ResponseEntity.ok(storageService.getFichiers());
    }

    @GetMapping("{idFichier}")
    public ResponseEntity getFichierById(@PathVariable("idFichier")Integer idFichier){
        return ResponseEntity.ok(storageService.getById(idFichier));
    }


    @GetMapping("/{idFichier}/download")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer idFichier) {
        Fichier fileDB = storageService.getFile(idFichier);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNom() + "\"")
                .body(fileDB.getDataFichier());
    }
}
