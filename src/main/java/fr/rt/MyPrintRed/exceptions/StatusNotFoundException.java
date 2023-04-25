package fr.rt.MyPrintRed.exceptions;

public class StatusNotFoundException extends RuntimeException{

    public StatusNotFoundException(Integer id){super("Could not find Status with ID :"+id);}
}
