package fr.rt.MyPrintRed.exceptions;

/**
 * @author slim
 * @Project
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id){
        super("Could not find the user with id "+ id);
    }
}
