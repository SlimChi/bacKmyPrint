package fr.rt.MyPrintRed.exceptions;

public class CommandeNotFoundException extends RuntimeException{

    public CommandeNotFoundException(Integer numeroCommande){super("Could not find Commande with numeroCommande :" +numeroCommande);}
}
