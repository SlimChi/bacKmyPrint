package fr.rt.MyPrintRed.services.password;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * @author slim
 * @Project
 */
@Getter
@Setter
public class NewPassword {

    @Email
    private String email;
    private String tokenPassword;
    private String password;

}
