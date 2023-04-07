package fr.rt.MyPrintRed.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author slimane
 * @Project emailApi
 */
@Getter
@Setter
@AllArgsConstructor
public class EmailMessage {

    private String to;
    private String email;
    private String subject;

    public EmailMessage(){

    }


}
