package fr.rt.MyPrintRed.exceptions;

public class FichierNotFoundException extends RuntimeException{

    public FichierNotFoundException(Integer id){super("Could not find Fichier with ID :"+id);}
}
