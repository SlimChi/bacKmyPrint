package fr.rt.MyPrintRed.exceptions;

public class OptionNotFoundException extends RuntimeException{

    public OptionNotFoundException(Integer id){super("Could not find Option with ID :"+id);}
}
