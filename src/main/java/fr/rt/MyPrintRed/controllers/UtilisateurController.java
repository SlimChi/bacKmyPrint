package fr.rt.MyPrintRed.controllers;


import fr.rt.MyPrintRed.dto.AdresseDto;
import fr.rt.MyPrintRed.dto.PasswordDto;
import fr.rt.MyPrintRed.dto.UtilisateurDto;
import fr.rt.MyPrintRed.dto.InsertUtilisateurDto;
import fr.rt.MyPrintRed.services.impl.AdresseSerImpl;
import fr.rt.MyPrintRed.request.AccountResponse;
import fr.rt.MyPrintRed.services.UtilisateurService;
import fr.rt.MyPrintRed.services.password.NewPassword;
import fr.rt.MyPrintRed.services.password.ResetPassword;
import fr.rt.MyPrintRed.validators.ObjectsValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static fr.rt.MyPrintRed.controllers.BaseUrl.*;

@RestController
@RequestMapping("/utilisateurs")
@RequiredArgsConstructor
@CrossOrigin(origins = FRONT_BASE_URL)
@Tag(name = "Utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AdresseSerImpl adresseSer;
    private final ObjectsValidator validator;


    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getUtilisateurs(HttpServletRequest request) {

        String uriBase = request.getRequestURL().toString();
        List<UtilisateurDto> utilisateurDtos = utilisateurService.getUtilisateurs();
        for(UtilisateurDto utilisateurDto : utilisateurDtos){

            utilisateurDto.addLink("all",uriBase);
            utilisateurDto.addLink("self",uriBase+"/"+utilisateurDto.getIdUtilisateur());
        }

        return ResponseEntity.ok(utilisateurDtos);
    }

    @GetMapping("{idUtilisateur}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(HttpServletRequest request,@PathVariable("idUtilisateur") Integer idUtilisateur) {

        String uriBase = request.getRequestURL().toString();
        try {
            UtilisateurDto utilisateurDto = utilisateurService.getUtilisateurById(idUtilisateur);
            utilisateurDto.addLink("all",uriBase.replace("/"+utilisateurDto.getIdUtilisateur(),""));
            utilisateurDto.addLink("adresses",ADRESSE_UTILISATEUR_BASE_URL+"/"+utilisateurDto.getIdUtilisateur());
            utilisateurDto.addLink("commandes",COMMANDE_BASE_URL+"/utilisateur/"+utilisateurDto.getIdUtilisateur());

            return ResponseEntity.ok(utilisateurDto);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{idUtilisateur}/update")
    public ResponseEntity<UtilisateurDto> updateUtilisateurById(@PathVariable("idUtilisateur") Integer idUtilisateur,
                                                @RequestBody InsertUtilisateurDto insertUtilisateurDto) {


        return ResponseEntity.ok(utilisateurService.updateUtilisateur(idUtilisateur, insertUtilisateurDto));


    }

    @PutMapping("{idUtilisateur}/updatepassword")
    public ResponseEntity<UtilisateurDto> updatePasswordUtilisateurById(@PathVariable("idUtilisateur") Integer idUtilisateur,
                                                @RequestBody PasswordDto passwordDto) {


        return ResponseEntity.ok(utilisateurService.updateUtilisateurPassword(idUtilisateur,passwordDto));


    }

    @PostMapping("/checkEmail")
    public AccountResponse resetPasswordEmail(@RequestBody ResetPassword resetPassword){
        validator.validate(resetPassword);
        var accountResponse = utilisateurService.checkEmail(resetPassword);
        return accountResponse;
    }

    @PostMapping("/resetPassword/{token}")
    public AccountResponse resetPassword(@RequestBody NewPassword newPassword, @PathVariable String token) {
        return utilisateurService.resetPassword(newPassword, token);
    }


    @PutMapping("/UpdateUserById/{idUtilisateur}")
    public ResponseEntity<Void> updateUser(@PathParam("user-id") Integer id,
                                     @RequestParam String firstName,
                                     @RequestParam String lastName,
                                     @RequestParam String email,
                                     @RequestParam String telephone
    ) {

        utilisateurService.updateUser(id, firstName, lastName, email, telephone);

        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserAndAddresses(@PathVariable Integer id) {
        utilisateurService.deleteUserAndAddresses(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/addAdresseToUser")
    public ResponseEntity<UtilisateurDto> addAdresseToUser(@RequestBody AdresseDto adresse) {

        adresseSer.addAdresseToUser(adresse);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/findall")
    public ResponseEntity<List<UtilisateurDto>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }


}
