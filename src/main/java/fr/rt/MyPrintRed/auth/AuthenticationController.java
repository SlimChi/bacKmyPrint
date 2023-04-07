package fr.rt.MyPrintRed.auth;

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


}
