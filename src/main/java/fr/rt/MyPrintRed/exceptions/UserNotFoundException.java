package fr.rt.MyPrintRed.exceptions;

/**
 * @author slim
 * @Project
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id){
        super("Could not found the user with id "+ id);
    }
}
