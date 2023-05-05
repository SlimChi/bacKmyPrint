package fr.rt.MyPrintRed.entities;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * @author slim
 * @Project
 */
@Getter
@Setter
public class ResetPassword {
    @Email
    private String email;
}
