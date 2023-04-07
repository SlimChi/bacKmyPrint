package fr.rt.MyPrintRed.controllers;


import fr.rt.MyPrintRed.dto.AdresseDto;
import fr.rt.MyPrintRed.services.AdresseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/adresses")
@RequiredArgsConstructor
@Tag(name = "address")
public class AdresseController {

    private final AdresseService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AdresseDto addressDto
    ) {
        return ResponseEntity.ok(service.save(addressDto));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<AdresseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AdresseDto> findById(
            @PathVariable("address-id") Integer addressId
    ) {
        return ResponseEntity.ok(service.findById(addressId));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("address-id") Integer addressId
    ) {
        service.delete(addressId);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/update")
    public ResponseEntity updateAdress(@PathParam("id")Integer id,
                                     @RequestParam String rue,
                                     @RequestParam String complement,
                                     @RequestParam String codePostal,
                                     @RequestParam String ville

                                     ){

        service.updateAdress(id,rue,complement,codePostal,ville);

        return ResponseEntity.ok().build();

    }

}
