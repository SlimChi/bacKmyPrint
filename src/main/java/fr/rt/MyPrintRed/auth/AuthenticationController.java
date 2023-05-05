package fr.rt.MyPrintRed.auth;

import fr.rt.MyPrintRed.request.AccountResponse;
import fr.rt.MyPrintRed.services.UtilisateurService;
import fr.rt.MyPrintRed.entities.NewPassword;
import fr.rt.MyPrintRed.entities.ResetPassword;
import fr.rt.MyPrintRed.validators.ObjectsValidator;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentification")
public class AuthenticationController {

    private final AuthenticationService service;
    private final ObjectsValidator validator;
    private final UtilisateurService utilisateurService;



    @PostMapping("register")

    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request){
        validator.validate(request);
        return ResponseEntity.ok(service.registerUser(request));

    }

    @PostMapping("register/admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@RequestBody RegisterRequest request){
        validator.validate(request);
        return ResponseEntity.ok(service.registerAdmin(request));

    }


    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){

        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return service.confirmToken(token);
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

}
