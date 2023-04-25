package fr.rt.MyPrintRed.exceptions;

public class CategorieNotFoundException extends RuntimeException{

    public CategorieNotFoundException(Integer id){super("Could not find Categorie with ID :"+id);}
}
