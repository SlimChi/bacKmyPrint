package fr.rt.MyPrintRed.repositories;

/**
 * @author Slimane
 * @Project ApiEmail
 */
public interface EmailSenderRepository {


    void sendEmail(String to, String email,String subject);

}
